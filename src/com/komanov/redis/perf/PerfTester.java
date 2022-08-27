package com.komanov.redis.perf;

import com.komanov.redis.StringUuidCodec$;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

public class PerfTester {
    private static class InFlight {
        public final int index;
        public long startTime;

        public InFlight(int index) {
            this.index = index;
        }
    }

    public static void main(String[] args) throws Throwable {
        assert args.length == 2;

        String setName = args[0];
        List<UUID> uuids = Files.readAllLines(Paths.get(args[1]))
                .stream()
                .map(UUID::fromString)
                .limit(1_000_000)
                .collect(Collectors.toList());

        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, UUID> connection = client.connect(StringUuidCodec$.MODULE$);
        RedisAsyncCommands<String, UUID> async = connection.async();

        System.out.println("About to run warmup");
        int warmupCount = 10_000;
        CountDownLatch latch = new CountDownLatch(warmupCount);
        uuids.stream().limit(warmupCount).forEach(v -> {
            async.sismember(setName, v).handle((r, e) -> {
                latch.countDown();
                return r;
            });
        });
        System.out.println("Awaiting for warmup to complete");
        latch.await();
        System.out.println("Warmup completed!");

        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (int parallelism = 0; parallelism <= 90; parallelism += 10) {
            results.add(runBenchmark(async, uuids, setName, parallelism == 0 ? 1 : parallelism));
        }
        for (int parallelism = 100; parallelism <= 1000; parallelism += 100) {
            results.add(runBenchmark(async, uuids, setName, parallelism));
        }
        results.add(runBenchmark(async, uuids, setName, 2000));

        for (int num = 0; num < results.get(0).size(); ++num) {
            for (int i = 0; i < results.size(); ++i) {
                ArrayList<String> single = results.get(i);
                System.out.print(single.get(num) + "\t");
            }
            System.out.println();
        }

        connection.close();
        client.shutdown();
    }

    private static ArrayList<String> runBenchmark(RedisAsyncCommands<String, UUID> async, List<UUID> uuids, String setName, int parallelism)
            throws Throwable {
        long[] durations = new long[uuids.size()];
        LinkedBlockingDeque<InFlight> queue = new LinkedBlockingDeque<>(parallelism);
        long overallStartTime = System.nanoTime();
        for (int i = 0; i < uuids.size(); i++) {
            final InFlight item = new InFlight(i);
            queue.put(item);
            item.startTime = System.nanoTime();
            async
                    .sismember(setName, uuids.get(i))
                    .handle((isMember, e) -> {
                        if (e == null) {
                            if (!isMember) {
                                System.out.println("NOT MEMBER!");
                                System.exit(1);
                            }
                            durations[item.index] = System.nanoTime() - item.startTime;
                            if (!queue.remove(item)) {
                                System.out.println("NOT IN QUEUE!");
                                System.exit(2);
                            }
                        } else {
                            e.printStackTrace();
                            System.exit(1);
                        }
                        return Boolean.TRUE;
                    })
            ;
        }

        while (!queue.isEmpty()) {
            Thread.sleep(0);
        }

        long overallDuration = System.nanoTime() - overallStartTime;

        ArrayList<String> results = new ArrayList<>();
        results.add("" + uuids.size());
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
}
