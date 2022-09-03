java_binary(
    name = "perf",
    srcs = glob(["*.java"]),
    jvm_flags = ["-Xmx2G"],
    main_class = "com.komanov.mysql.hashset.perf.PerfTester",
    deps = [
        "//src/com/komanov/mysql/hashset",
        "@mysql_hashset_maven//:com_zaxxer_HikariCP",
        "@mysql_hashset_maven//:mysql_mysql_connector_java",
    ],
)
