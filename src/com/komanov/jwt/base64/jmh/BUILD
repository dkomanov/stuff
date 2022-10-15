scala_benchmark_jmh(
    name = "jmh",
    srcs = ["Base64Benchmarks.scala"],
    deps = [
        ":jmh_base",
        "//src/com/komanov/jwt/base64",
    ],
)

scala_library(
    name = "jmh_base",
    srcs = ["Base64BenchmarkBase.scala"],
    visibility = ["//src/com/komanov/jwt/base64:__subpackages__"],
    exports = [
        "//src/com/komanov/jmh",
    ],
    deps = [
        "//src/com/komanov/jmh",
        "//src/com/komanov/jwt/base64",
        "//src/com/komanov/jwt/base64:generator",
    ],
)
