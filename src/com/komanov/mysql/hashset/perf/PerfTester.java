package com.komanov.mysql.hashset.perf;

import com.komanov.mysql.hashset.UuidHelper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PerfTester {
    public static final int MinParallelism = 1;
    public static final int MaxParallelism = 10;

    public static void main(String[] args) throws Throwable {
        assert args.length == 5;

        String user = args[0];
        String password = args[1];
        boolean usePool = args[2].equals("hikari");
        String setName = args[3];
        String tableName = setName.replace("-", "");
        List<String> allLines = Files.readAllLines(Paths.get(args[4]));
        List<UUID> uuids = allLines
                .stream()
                .map(UUID::fromString)
                .limit(1_000_000)
                .collect(Collectors.toList());

        String url = "jdbc:mysql://localhost:3306/hash_set?user=" + user + "&password=" + password + "&cacheServerConfiguration=true&createDatabaseIfNotExist=false&cachePrepStmts=true&useServerPrepStmts=true&useSSL=false&useLocalSessionState=true&useLocalTransactionState=true";
        ConnectionPool cp = usePool
                ? new HikariConnectionPool(url)
                : new ThreadLocalConnectionPool(url);
        ExecutorService es = Executors.newFixedThreadPool(MaxParallelism);
        Benchmark b = new Benchmark(cp, es, tableName);

        warmup(b, uuids.stream().limit(10_000));

        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (int parallelism = MinParallelism; parallelism <= MaxParallelism; ++parallelism) {
            results.add(runBenchmark(b, uuids, allLines.size(), parallelism));
        }

        for (int num = 0; num < results.get(0).size(); ++num) {
            for (int i = 0; i < results.size(); ++i) {
                ArrayList<String> single = results.get(i);
                System.out.print(single.get(num) + "\t");
            }
            System.out.println();
        }

        cp.shutdown();
        es.shutdown();
    }

    private static ArrayList<String> runBenchmark(Benchmark b, List<UUID> uuids, int setSize, int parallelism) {
        long[] durations = new long[uuids.size()];
        AtomicInteger atomicIndex = new AtomicInteger();
        int size = uuids.size();

        long overallStartTime = System.nanoTime();

        List<Future<?>> workers = IntStream.range(0, parallelism).mapToObj(workerNumber -> b.es.submit(() -> {
            try {
                int index;
                int count = 0;
                while ((index = atomicIndex.getAndIncrement()) < size) {
                    long startTime = System.nanoTime();
                    b.existsBlocking(uuids.get(index));
                    long duration = System.nanoTime() - startTime;
                    durations[index] = duration;
                    if (index % 100_000 == 0) {
                        debug("processed " + index + " at worker " + workerNumber);
                    }
                    ++count;
                }
                debug("worker " + workerNumber + " completed " + count);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        })).collect(Collectors.toList());

        workers.forEach(w -> {
            try {
                w.get();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });

        long overallDuration = System.nanoTime() - overallStartTime;

        ArrayList<String> results = new ArrayList<>();
        results.add("" + setSize);
        results.add("" + parallelism);
        results.add("" + overallDuration);
        results.add("" + overallDuration / uuids.size());

        Arrays.sort(durations);
        Arrays.stream(durations).limit(5).forEach(d -> {
            results.add("" + d);
        });
        Arrays.stream(durations).skip(durations.length / 2).limit(5).forEach(d -> {
            results.add("" + d);
        });
        Arrays.stream(durations).skip(durations.length - 5).forEach(d -> {
            results.add("" + d);
        });
        return results;
    }

    private static void warmup(Benchmark b, Stream<UUID> uuidStream) {
        debug("About to run warmup");
        uuidStream.map(b::exists).forEach(f -> {
            try {
                f.get();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
        debug("Warmup completed!");
    }

    private static class Benchmark {
        public final ConnectionPool cp;
        public final ExecutorService es;
        public final String tableName;

        public Benchmark(ConnectionPool cp, ExecutorService es, String tableName) {
            this.cp = cp;
            this.es = es;
            this.tableName = tableName;
        }

        public boolean existsBlocking(UUID uuid) throws Throwable {
            Connection conn = cp.acquire();
            try {
                try (PreparedStatement st = conn.prepareStatement("SELECT 1 FROM " + tableName + " WHERE id = ?")) {
                    st.setBytes(1, UuidHelper.toBytes(uuid));
                    try (ResultSet rs = st.executeQuery()) {
                        if (!rs.next()) {
                            throw new RuntimeException("rs.next = false");
                        }
                        if (!rs.getBoolean(1)) {
                            throw new RuntimeException("miss!");
                        }
                    }
                }
            } finally {
                cp.release(conn);
            }

            return true;
        }

        public Future<Boolean> exists(UUID uuid) {
            return es.submit(() -> {
                try {
                    return existsBlocking(uuid);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void debug(String s) {
        System.err.println(java.time.LocalDateTime.now() + ": " + s);
    }
}
