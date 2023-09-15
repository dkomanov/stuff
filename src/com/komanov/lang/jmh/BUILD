scala_benchmark_jmh(
    name = "jmh",
    srcs = glob(["*.scala"]),
    deps = [
        ":javalib",
        "//src/com/komanov/jmh",
        "@io_bazel_rules_scala_org_openjdk_jmh_jmh_core",
    ],
)

java_library(
    name = "javalib",
    srcs = glob(["*.java"]),
    deps = [
        "@io_bazel_rules_scala_org_openjdk_jmh_jmh_core",
    ],
)
