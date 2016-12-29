scala_library(
    name = "stringformat",
    srcs = glob([
        "*.scala",
        "*.java",
    ]),
    visibility = [
        "//src/com/komanov/stringformat/bin:__pkg__",
        "//src/com/komanov/stringformat/jmh:__pkg__",
        "//src/com/komanov/stringformat/tests:__pkg__",
    ],
    deps = [
        "@org_slf4j_slf4j_api//jar",
        "//src/com/komanov/stringformat/macros",
    ],
)
