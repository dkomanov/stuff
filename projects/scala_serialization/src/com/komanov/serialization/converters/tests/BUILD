scala_specs2_junit(
    name = "tests",
    srcs = glob(["*.scala"]),
    runtime_deps = [
        "@org_slf4j_slf4j_api//jar",
    ],
    deps = [
        "//src/com/komanov/serialization/converters",
        "//src/com/komanov/serialization/domain",
        "//src/com/komanov/serialization/domain:domain_java_thrift",
        "//src/com/komanov/serialization/domain/testdata",
        "//src/com/komanov/serialization/proto:protos_java",
        "//src/com/komanov/serialization/proto:protos_scala",
        "//src/com/komanov/serialization/proto:thrift_scrooge",
        "//third_party:com_fasterxml_jackson_core_jackson_core",
        "//third_party:com_fasterxml_jackson_core_jackson_databind",
        "//third_party:com_fasterxml_jackson_module_jackson_module_scala",
        "//third_party:com_twitter_chill",
        "//third_party:org_apache_thrift_libthrift",
        "@io_suzaku_boopickle//jar",
    ],
)
