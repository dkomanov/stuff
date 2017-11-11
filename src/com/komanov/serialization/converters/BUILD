dependencies = [
    "@me_chrons_boopickle_2_11//jar:file",
    "@org_scala_lang_modules_scala_pickling_2_11//jar:file",
    "//src/com/komanov/serialization/domain",
    "//src/com/komanov/serialization/domain:domain_java_thrift",
    "//src/com/komanov/serialization/domain:domain_scala_thrift",
    "//src/com/komanov/serialization/proto:protos_java",
    "//src/com/komanov/serialization/proto:protos_scala",
    "//third_party:com_fasterxml_jackson_core_jackson_core",
    "//third_party:com_fasterxml_jackson_core_jackson_databind",
    "//third_party:com_fasterxml_jackson_module_jackson_module_scala_2_11",
    "//third_party:com_twitter_chill_2_11",
    "//third_party:com_twitter_chill_java",
    "//third_party:com_twitter_scrooge_core_2_11",
    "//third_party:org_apache_thrift_libthrift",
]

scala_library(
    name = "converters",
    srcs = glob([
        "*.scala",
        "pickling/*.scala",
    ]),
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    exports = dependencies,
    deps = dependencies,
)
