scala_specs2_junit(
    name = "tests",
    srcs = glob(["*.scala"]) + ["TestSuite.java"],
    test_class = "com.komanov.stringformat.tests.TestSuite",
    runtime_deps = [
        "@org_slf4j_slf4j_api//jar",
    ],
    deps = [
        "//src/com/komanov/stringformat",
    ],
)
