java_library(
    name = "lib",
    srcs = ["InputType.java"],
)

scala_benchmark_jmh(
    name = "jmh",
    srcs = glob(["*.scala"]),
    deps = [
        "lib",
        "//src/com/komanov/readlines",
    ],
)
