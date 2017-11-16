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
    "//third_party:io_circe_circe_core_2_11",
    "@io_circe_circe_generic_2_11//jar:file",
    "@com_chuusai_shapeless_2_11//jar:file",
    "//third_party:io_circe_circe_generic_2_11",
    "//third_party:io_circe_circe_parser_2_11",
    "//third_party:org_typelevel_cats_core_2_11",
    "//third_party:org_typelevel_cats_macros_2_11",
    "//third_party:org_typelevel_machinist_2_11",
    "//third_party:org_apache_thrift_libthrift",
]

scala_library(
    name = "converters",
    srcs = glob([
        "*.scala",
        "pickling/*.scala",
    ]),
    scalac_jvm_flags = [
        "-Xss256m",
    ],
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    exports = dependencies,
    deps = dependencies,
)
