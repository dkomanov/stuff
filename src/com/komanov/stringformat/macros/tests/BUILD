scala_library(
    name = "tests-library",
    srcs = ["MacrosConcatTest.scala"],
    deps = [
        "//src/com/komanov/stringformat/macros",
        "//third_party:org_specs2_specs2_junit_2_11",
        "//third_party:org_specs2_specs2_matcher_extra_2_11",
        "//third_party:org_specs2_specs2_mock_2_11",
    ],
)

java_import(
    name = "tests-library-import",
    jars = [":tests-library.jar"],
    runtime_deps = [
        "//third_party:org_specs2_specs2_junit_2_11",
        "//third_party:org_specs2_specs2_matcher_extra_2_11",
        "//third_party:org_specs2_specs2_mock_2_11",
    ],
)

java_test(
    name = "tests",
    size = "small",
    test_class = "src.com.komanov.stringformat.macros.tests.MacrosConcatTest",
    runtime_deps = [
        ":tests-library-import",
    ],
)
