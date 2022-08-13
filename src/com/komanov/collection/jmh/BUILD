scala_benchmark_jmh(
    name = "jmh",
    srcs = glob(["*.scala"]),
    deps = [
        ":helper",
    ],
)

java_library(
    name = "helper",
    srcs = ["JavaHelper.java"],
    deps = [
        "@io_bazel_rules_scala_scala_library",
    ],
    visibility = ["//visibility:public"],
)
