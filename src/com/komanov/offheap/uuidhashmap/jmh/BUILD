scala_benchmark_jmh(
    name = "jmh",
    srcs = glob(["*.scala"]),
    deps = [
        ":helper",
        "//src/com/komanov/offheap/uuidhashmap",
    ],
)

java_library(
    name = "helper",
    srcs = ["JavaHelper.java"],
    visibility = ["//visibility:public"],
    deps = [
        "//src/com/komanov/offheap/uuidhashmap",
        "@io_bazel_rules_scala_scala_library",
    ],
)
