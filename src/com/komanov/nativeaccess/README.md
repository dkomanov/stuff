To run:
```
bazel run //src/com/komanov/nativeaccess/jmh -- -jvmArgsAppend -Djava.library.path=rs/getloadavg
```

Inspired by https://github.com/zakgof/java-native-benchmark, but gradle is just a mess (I really tried, but in IntelliJ it's 4.8, latest is 7 and jmh plugin doesn't work properly.)
