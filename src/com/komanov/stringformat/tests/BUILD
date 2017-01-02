specs2_dependencies = [
    "//third_party:org_specs2_specs2_junit_2_11",
    "//third_party:org_specs2_specs2_matcher_extra_2_11",
    "//third_party:org_specs2_specs2_mock_2_11",
]

scala_library(
    name = "tests-library",
    srcs = glob(["*.scala"]),
    deps = ["//src/com/komanov/stringformat"] + specs2_dependencies,
)

java_import(
    name = "stringformat-library-import",
    jars = ["//src/com/komanov/stringformat:stringformat.jar"],
    runtime_deps = [
        "@org_slf4j_slf4j_api//jar",
    ],
)

java_import(
    name = "tests-library-import",
    jars = [":tests-library.jar"],
    runtime_deps = [":stringformat-library-import"] + specs2_dependencies,
)

java_test(
    name = "tests",
    size = "small",
    srcs = ["TestSuite.java"],
    test_class = "com.komanov.stringformat.tests.TestSuite",
    deps = [
        ":tests-library-import",
    ],
)
