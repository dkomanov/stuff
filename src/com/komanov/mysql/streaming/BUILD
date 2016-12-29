scala_library(
    name = "streaming",
    srcs = glob(["*.scala"]),
    visibility = [
        "//src/com/komanov/mysql/streaming/bin:__pkg__",
        "//src/com/komanov/mysql/streaming/jmh:__pkg__",
        "//src/com/komanov/mysql/streaming/tests:__pkg__",
    ],
    deps = [
        "//third_party:com_wix_wix_embedded_mysql",
        "//third_party:mysql_mysql_connector_java",
        "//third_party:org_drizzle_jdbc_drizzle_jdbc",
        "//third_party:org_mariadb_jdbc_mariadb_java_client",
    ],
)
