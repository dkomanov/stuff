scala_benchmark_jmh(
    name = "jmh",
    srcs = ["Base64JniBenchmarks.scala"],
    deps = [
        "//src/com/komanov/jwt/base64",
        "//src/com/komanov/jwt/base64/jmh:jmh_base",
        "//src/com/komanov/jwt/base64/jni",
    ],
)

scala_benchmark_jmh(
    name = "bazel_vs_cargo",
    srcs = ["BazelVsCargoBenchmarks.scala"],
    deps = [
        ":native_lib",
        "//src/com/komanov/jwt/base64",
        "//src/com/komanov/jwt/base64/jmh:jmh_base",
        "//src/com/komanov/jwt/base64/jni",
    ],
)

java_library(
    name = "native_lib",
    srcs = ["NativeLib.java"],
    deps = ["//src/com/komanov/jwt/base64/jni"],
)
