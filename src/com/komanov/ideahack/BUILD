# In this target enumerated all root dependencies from //third_party.
# This is necessary for IntelliJ plugin to recognize all libraries:
# it sees dependencies only from java rules (scala rules aren't supported).

java_binary(
    name = "ideahack",
    srcs = ["Main.java"],
    main_class = "com.komanov.ideahack.Main",
    deps = [
        "//third_party:com_fasterxml_jackson_core_jackson_core",
        "//third_party:com_fasterxml_jackson_core_jackson_databind",
        "//third_party:com_fasterxml_jackson_module_jackson_module_scala_2_11",
        "//third_party:com_twitter_chill_2_11",
        "//third_party:com_twitter_scrooge_core_2_11",
        "//third_party:com_wix_wix_embedded_mysql",
        "//third_party:commons_io_commons_io",
        "//third_party:me_chrons_boopickle_2_11",
        "//third_party:mysql_mysql_connector_java",
        "//third_party:org_apache_thrift_libthrift",
        "//third_party:org_drizzle_jdbc_drizzle_jdbc",
        "//third_party:org_mariadb_jdbc_mariadb_java_client",
        "//third_party:org_scala_lang_modules_scala_pickling_2_11",
        "//third_party:org_slf4j_slf4j_api",
        "//third_party:org_slf4j_slf4j_nop",
        "//third_party:org_slf4j_slf4j_simple",
        "@io_bazel_rules_scala_org_openjdk_jmh_jmh_core//jar",
        "@io_bazel_rules_scala_org_scalaz_scalaz_core//jar",
        "@io_bazel_rules_scala_org_scalaz_scalaz_effect//jar",
        "@io_bazel_rules_scala_org_specs2_specs2_common//jar",
        "@io_bazel_rules_scala_org_specs2_specs2_core//jar",
        "@io_bazel_rules_scala_org_specs2_specs2_junit_2_11//jar",
        "@io_bazel_rules_scala_org_specs2_specs2_matcher//jar",
        "@scala//:scala-library",
    ],
)
