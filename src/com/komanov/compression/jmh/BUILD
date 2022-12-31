scala_benchmark_jmh(
    name = "jmh",
    srcs = ["CompressionBenchmark.scala"],
    deps = [
        ":input",
        "//src/com/komanov/compression",
        "//src/com/komanov/mysql/blob",
    ],
)

java_library(
    name = "input",
    srcs = ["InputData.java"],
    visibility = ["//visibility:public"],
)
