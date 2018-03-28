dependencies = [
    "@io_suzaku_boopickle//jar:file",
    "//src/com/komanov/serialization/domain",
    "//src/com/komanov/serialization/domain:domain_java_thrift",
    "//src/com/komanov/serialization/proto:protos_java",
    "//src/com/komanov/serialization/proto:protos_scala",
    "//src/com/komanov/serialization/proto:thrift_scrooge",
    "//third_party:com_fasterxml_jackson_core_jackson_core",
    "//third_party:com_fasterxml_jackson_core_jackson_databind",
    "//third_party:com_fasterxml_jackson_module_jackson_module_scala",
    "//third_party:com_twitter_chill",
    "//third_party:com_twitter_chill_java",
    "//third_party:io_circe_circe_core",
    "@io_circe_circe_generic//jar:file",
    "@com_chuusai_shapeless//jar:file",
    "//third_party:io_circe_circe_generic",
    "//third_party:io_circe_circe_parser",
    "//third_party:org_typelevel_cats_core",
    "//third_party:org_typelevel_cats_macros",
    "//third_party:org_typelevel_machinist",
    "//third_party:org_apache_thrift_libthrift",
]

scala_library(
    name = "converters",
    srcs = glob(["*.scala"]),
    scalac_jvm_flags = [
        "-Xss256m",
    ],
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    exports = dependencies,
    deps = dependencies,
)
