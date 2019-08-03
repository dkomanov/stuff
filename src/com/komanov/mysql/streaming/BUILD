scala_library(
    name = "streaming",
    srcs = glob(["*.scala"]),
    visibility = [
        "//src/com/komanov/mysql/streaming/bin:__pkg__",
        "//src/com/komanov/mysql/streaming/jmh:__pkg__",
        "//src/com/komanov/mysql/streaming/tests:__pkg__",
    ],
    deps = [
        "@mysql_streaming_maven//:com_wix_wix_embedded_mysql",
        "@mysql_streaming_maven//:de_flapdoodle_embed_de_flapdoodle_embed_process",
        "@mysql_streaming_maven//:mysql_mysql_connector_java",
        "@mysql_streaming_maven//:org_drizzle_jdbc_drizzle_jdbc",
        "@mysql_streaming_maven//:org_mariadb_jdbc_mariadb_java_client",
    ],
)
