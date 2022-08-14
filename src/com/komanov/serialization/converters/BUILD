all_converters = [
    "//src/com/komanov/serialization/converters/api",
    ":boopickle",
    ":chill",
    ":circe",
    ":java_pb",
    ":java_serialization",
    ":java_thrift",
    ":jackson",
    ":jsoniter_scala",
    ":scala_pb",
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

scala_macro_library(
    name = "boopickle",
    srcs = ["BoopickleConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "@scala_serialization_maven//:io_suzaku_boopickle_2_13",
    ],
)

scala_library(
    name = "chill",
    srcs = ["ChillConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "@scala_serialization_maven//:com_twitter_chill_2_13",
        "@scala_serialization_maven//:com_twitter_chill_java",
    ],
)

scala_library(
    name = "circe",
    srcs = ["CirceConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "@scala_serialization_maven//:com_chuusai_shapeless_2_13",
        "@scala_serialization_maven//:io_circe_circe_core_2_13",
        "@scala_serialization_maven//:io_circe_circe_generic_2_13",
        "@scala_serialization_maven//:io_circe_circe_parser_2_13",
        "@scala_serialization_maven//:org_typelevel_cats_core_2_13",
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
        "@scala_serialization_maven//:com_google_protobuf_protobuf_java",
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
        "@scala_serialization_maven//:org_apache_thrift_libthrift",
    ],
)

scala_library(
    name = "jackson",
    srcs = ["JsonConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "@scala_serialization_maven//:com_fasterxml_jackson_core_jackson_core",
        "@scala_serialization_maven//:com_fasterxml_jackson_core_jackson_databind",
        "@scala_serialization_maven//:com_fasterxml_jackson_dataformat_jackson_dataformat_cbor",
        "@scala_serialization_maven//:com_fasterxml_jackson_dataformat_jackson_dataformat_smile",
        "@scala_serialization_maven//:com_fasterxml_jackson_module_jackson_module_scala_2_13",
    ],
)

scala_macro_library(
    name = "jsoniter_scala",
    srcs = ["JsoniterScalaConverter.scala"],
    deps = [
        "//src/com/komanov/serialization/converters/api",
        "@scala_serialization_maven//:com_github_plokhotnyuk_jsoniter_scala_jsoniter_scala_core_2_13",
        "@scala_serialization_maven//:com_github_plokhotnyuk_jsoniter_scala_jsoniter_scala_macros_2_13",
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
        "@scala_serialization_maven//:com_google_protobuf_protobuf_java",
    ],
)

scala_library(
    name = "reflection_utils",
    srcs = ["ReflectionUtils.scala"],
)
