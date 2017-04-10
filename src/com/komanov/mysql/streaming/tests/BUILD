scala_specs2_junit(
    name = "tests",
    size = "large",
    srcs = glob(["*.scala"]),
    runtime_deps = [
        "//third_party:mysql_mysql_connector_java",
        "//third_party:org_drizzle_jdbc_drizzle_jdbc",
        "//third_party:org_mariadb_jdbc_mariadb_java_client",
    ],
    deps = [
        "//src/com/komanov/mysql/streaming",
        "//third_party:com_wix_wix_embedded_mysql",
    ],
)
