enums_deps = [
    "//src/com/komanov/serialization/converters",
    "//src/com/komanov/serialization/domain",
    "//src/com/komanov/serialization/domain/testdata",
]

scala_library(
    name = "enums",
    srcs = glob([
        "*.java",
    ]),
    visibility = ["//src/com/komanov/serialization/jmh:__subpackages__"],
    exports = enums_deps,
    deps = enums_deps,
)

scala_benchmark_jmh(
    name = "jmh",
    srcs = glob([
        "*.scala",
    ]),
    deps = [
        ":enums",
    ],
)
