## Redis Benchmark for Set (SISMEMBER command)

Prepare data:
```
bazel run //src/com/komanov/redis/bin -- set-1m ~/uuid1m.txt
bazel run //src/com/komanov/redis/bin -- set-10m ~/uuid10m.txt
bazel run //src/com/komanov/redis/bin -- set-100k ~/uuid100k.txt
```

Run Redis in docker:
```
docker run --name my-redis -p 6379:6379 redis:7.0-alpine
```

Run cli:
```
docker exec -it my-redis redis-cli
```

Run benchmark:
```
time bazel run //src/com/komanov/redis/perf set-1m ~/uuid1m.txt
time bazel run //src/com/komanov/redis/perf set-10m ~/uuid10m.txt
time bazel run //src/com/komanov/redis/perf set-100k ~/uuid100k.txt
```

Results: https://docs.google.com/spreadsheets/d/1D5fhP-rxuxamOl58cGk7yLiLiViZnxWcE71klp6JC3I
