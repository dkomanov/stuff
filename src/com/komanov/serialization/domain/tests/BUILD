specs2_dependencies = [
    "//third_party:org_specs2_specs2_junit_2_11",
    "//third_party:org_specs2_specs2_matcher_extra_2_11",
    "//third_party:org_specs2_specs2_mock_2_11",
]

scala_library(
    name = "tests-library",
    srcs = glob(["*.scala"]),
    deps = specs2_dependencies + [
        "//src/com/komanov/serialization/domain",
        "//src/com/komanov/serialization/domain/testdata",
    ],
)

java_import(
    name = "tests-library-import",
    jars = [
        "//src/com/komanov/serialization/domain:domain.jar",
        "//src/com/komanov/serialization/domain/testdata:testdata.jar",
        ":tests-library.jar",
    ],
)

java_test(
    name = "tests",
    size = "small",
    test_class = "com.komanov.serialization.domain.tests.EventProcessorTest",
    runtime_deps = specs2_dependencies + [
        ":tests-library-import",
    ],
)
