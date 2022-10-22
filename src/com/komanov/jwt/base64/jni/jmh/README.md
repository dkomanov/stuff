## JNI benchmark

To run this benchmark and have results for `base64-simd`, you need to build [rust-stuff](https://github.com/dkomanov/rust-stuff) repo with `cargo build -r` and then set proper path for `-Djava.library.path` (assuming rust-stuff is in the same parent directory as stuff, we can use `$PWD`):

```bash
bazel run //src/com/komanov/jwt/base64/jni/jmh -- -jvmArgsAppend "-Djava.library.path=bazel-bin/rs/base64:rs/base64:$PWD/../rust-stuff/target/release"
```

What's the problem with `base64-simd`? I can't make it work properly while building it with bazel. To ensure that bazel sucks, there is a benchmark:

```bash
bazel run //src/com/komanov/jwt/base64/jni/jmh:bazel_vs_cargo -- -jvmArgsAppend "-Djava.library.path=bazel-bin/rs/base64:rs/base64:$PWD/../rust-stuff/target/release"
```

To no wait for comparison forever:
```bash
bazel run //src/com/komanov/jwt/base64/jni/jmh:bazel_vs_cargo -- -jvmArgsAppend "-Djava.library.path=bazel-bin/rs/base64:rs/base64:$PWD/../rust-stuff/target/release" -p length=10000 -p dataset=fixed
```
