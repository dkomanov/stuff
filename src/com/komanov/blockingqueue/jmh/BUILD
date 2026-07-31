scala_benchmark_jmh(
    name = "jmh",
    srcs = glob(["*.scala"]),
    deps = [
        "enums",
    ],
)

java_library(
    name = "enums",
    srcs = ["QueueType.java"],
    visibility = ["//src/com/komanov/blockingqueue/jmh:__subpackages__"],
)
