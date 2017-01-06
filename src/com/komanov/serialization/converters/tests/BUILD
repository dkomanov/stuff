dependencies = [
    "@me_chrons_boopickle_2_11//jar",
    "@org_scala_lang_modules_scala_pickling_2_11//jar",
    "//src/com/komanov/serialization/proto:protos_java",
    "//src/com/komanov/serialization/domain:domain_java_thrift",
    "//third_party:com_fasterxml_jackson_core_jackson_core",
    "//third_party:com_fasterxml_jackson_core_jackson_databind",
    "//third_party:com_fasterxml_jackson_module_jackson_module_scala_2_11",
    "//third_party:com_google_protobuf_protobuf_java",
    "//third_party:com_trueaccord_scalapb_scalapb_runtime_2_11",
    "//third_party:com_twitter_chill_2_11",
    "//third_party:com_twitter_scrooge_core_2_11",
    "//third_party:org_apache_thrift_libthrift",
    "//third_party:commons_io_commons_io",
]

scala_library(
    name = "tests-library",
    srcs = glob([
        "ConversionsUtilsTest.scala",
        "SerializationTest.scala",
    ]),
    deps = dependencies + [
        "//src/com/komanov/serialization/converters",
        "//src/com/komanov/serialization/domain",
        "//src/com/komanov/serialization/domain/testdata",
        "//third_party:org_specs2_specs2_junit_2_11",
        "//third_party:org_specs2_specs2_matcher_extra_2_11",
        "//third_party:org_specs2_specs2_mock_2_11",
    ],
)

java_import(
    name = "converters-library-import",
    jars = [
        "//src/com/komanov/serialization/converters:converters.jar",
        "//src/com/komanov/serialization/domain:domain.jar",
        "//src/com/komanov/serialization/domain:domain_scala_pb.jar",
        "//src/com/komanov/serialization/domain:domain_scala_thrift.jar",
        "//src/com/komanov/serialization/domain/testdata:testdata.jar",
    ],
    runtime_deps = [
        "@org_slf4j_slf4j_api//jar",
    ],
)

java_import(
    name = "tests-library-import",
    jars = [":tests-library.jar"],
    runtime_deps = [
        ":converters-library-import",
        "//third_party:org_specs2_specs2_junit_2_11",
        "//third_party:org_specs2_specs2_matcher_extra_2_11",
        "//third_party:org_specs2_specs2_mock_2_11",
    ],
)

java_test(
    name = "tests",
    size = "small",
    srcs = ["TestSuite.java"],
    test_class = "com.komanov.serialization.converters.tests.TestSuite",
    runtime_deps = dependencies,
    deps = [
        ":tests-library-import",
    ],
)
