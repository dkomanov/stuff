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
        "@maven//:org_slf4j_slf4j_api",
        "//src/com/komanov/stringformat/macros",
    ],
)
