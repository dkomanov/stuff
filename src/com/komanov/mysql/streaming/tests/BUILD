scala_library(
    name = "tests-library",
    srcs = glob(["QueryTest.scala"]),
    deps = [
        "//src/com/komanov/mysql/streaming",
        "//third_party:com_wix_wix_embedded_mysql",
        "//third_party:org_specs2_specs2_junit_2_11",
        "//third_party:org_specs2_specs2_matcher_extra_2_11",
        "//third_party:org_specs2_specs2_mock_2_11",
    ],
)

java_import(
    name = "parent-library-import",
    jars = ["//src/com/komanov/mysql/streaming:streaming.jar"],
    runtime_deps = [
        "//third_party:com_wix_wix_embedded_mysql",
        "//third_party:mysql_mysql_connector_java",
        "//third_party:org_drizzle_jdbc_drizzle_jdbc",
        "//third_party:org_mariadb_jdbc_mariadb_java_client",
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
    size = "large",
    test_class = "com.komanov.mysql.streaming.tests.QueryTest",
    runtime_deps = [
        ":parent-library-import",
        ":tests-library-import",
    ],
)
