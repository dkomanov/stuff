all_converters = [
    "//src/com/komanov/serialization/converters/api",
    ":boopickle",
    ":chill",
    #":circe",
    ":java_pb",
    ":java_serialization",
    ":java_thrift",
    ":jackson",
    ":jsoniter_scala",
    ":scala_pb",
    ":scrooge",
]

scala_library(
    name = "converters",
    srcs = ["Converters.scala"],
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    exports = all_converters,
    deps = all_converters,
)

#
# Converters by library
#

scala_library(
    name = "boopickle",
    srcs = ["BoopickleConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "@io_suzaku_boopickle//jar:file",
    ],
)

scala_library(
    name = "chill",
    srcs = ["ChillConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "//third_party:com_twitter_chill",
        "//third_party:com_twitter_chill_java",
    ],
)

scala_library(
    name = "circe",
    srcs = ["CirceConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "//third_party:io_circe_circe_core",
        "//third_party:io_circe_circe_generic",
        "//third_party:io_circe_circe_parser",
        "//third_party:org_typelevel_cats_core",
        "//third_party:org_typelevel_cats_macros",
        "//third_party:org_typelevel_machinist",
        "@com_chuusai_shapeless//jar:file",
        "@io_circe_circe_generic//jar:file",
    ],
)

scala_library(
    name = "java_pb",
    srcs = ["JavaPbConverter.scala"],
    deps = [
        ":conversion_utils",
        ":protobuf_conversion_utils",
        "//src/com/komanov/serialization/converters/api",
        "//src/com/komanov/serialization/proto:protos_java",
    ],
)

scala_library(
    name = "scala_pb",
    srcs = ["ScalaPbConverter.scala"],
    deps = [
        ":conversion_utils",
        ":protobuf_conversion_utils",
        ":reflection_utils",
        "//src/com/komanov/serialization/converters/api",
        "//src/com/komanov/serialization/proto:protos_scala",
    ],
)

scala_library(
    name = "java_serialization",
    srcs = ["JavaSerializationConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "//src/com/komanov/serialization/io",
    ],
)

scala_library(
    name = "java_thrift",
    srcs = ["JavaThriftConverter.scala"],
    deps = [
        ":conversion_utils",
        "//src/com/komanov/serialization/converters/api",
        "//src/com/komanov/serialization/domain:domain_java_thrift",
        "//third_party:org_apache_thrift_libthrift",
    ],
)

scala_library(
    name = "jackson",
    srcs = ["JsonConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "//third_party:com_fasterxml_jackson_core_jackson_core",
        "//third_party:com_fasterxml_jackson_core_jackson_databind",
        "//third_party:com_fasterxml_jackson_dataformat_jackson_dataformat_cbor",
        "//third_party:com_fasterxml_jackson_module_jackson_module_scala",
    ],
)

scala_library(
    name = "jsoniter_scala",
    srcs = ["JsoniterScalaConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "@com_github_plokhotnyuk_jsoniter_scala_core//jar:file",
        "@com_github_plokhotnyuk_jsoniter_scala_macros//jar:file",
    ],
)

scala_library(
    name = "scrooge",
    srcs = ["ScroogeConverter.scala"],
    deps = [
        ":conversion_utils",
        ":reflection_utils",
        "//src/com/komanov/serialization/converters/api",
        "//src/com/komanov/serialization/proto:thrift_scrooge",
    ],
)

#
# Support modules
#

scala_library(
    name = "conversion_utils",
    srcs = ["ConversionUtils.scala"],
    visibility = ["//src/com/komanov/serialization/converters:__subpackages__"],
)

scala_library(
    name = "protobuf_conversion_utils",
    srcs = ["ProtobufConversionUtils.scala"],
    visibility = ["//src/com/komanov/serialization/converters:__subpackages__"],
    deps = [
        ":conversion_utils",
        "@com_google_protobuf_protobuf_java//jar",
    ],
)

scala_library(
    name = "reflection_utils",
    srcs = ["ReflectionUtils.scala"],
)
