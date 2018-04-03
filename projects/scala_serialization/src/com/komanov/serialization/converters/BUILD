dependencies = [
    ":api",
    ":boopickle",
    ":chill",
    #":circe",
    ":java_pb",
    ":java_serialization",
    ":java_thrift",
    ":jackson",
    ":scala_pb",
    ":scrooge",
]

scala_library(
    name = "converters",
    srcs = ["Converters.scala"],
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    exports = dependencies,
    deps = dependencies,
)

scala_library(
    name = "api",
    srcs = glob([
        "EventConverter.scala",
        "MyConverter.scala",
        "SiteConverter.scala",
    ]),
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    exports = [
        "//src/com/komanov/serialization/domain",
    ],
    deps = [
        "//src/com/komanov/serialization/domain",
    ],
)

scala_library(
    name = "boopickle",
    srcs = ["BoopickleConverter.scala"],
    deps = [
        ":api",
        "@io_suzaku_boopickle//jar:file",
    ],
)

scala_library(
    name = "chill",
    srcs = ["ChillConverter.scala"],
    deps = [
        ":api",
        "//third_party:com_twitter_chill",
        "//third_party:com_twitter_chill_java",
    ],
)

scala_library(
    name = "circe",
    srcs = ["CirceConverter.scala"],
    deps = [
        ":api",
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
    name = "protobuf_utils",
    srcs = ["ConversionUtils.scala"],
    visibility = ["//src/com/komanov/serialization/converters:__subpackages__"],
)

scala_library(
    name = "protobuf_conversion_utils",
    srcs = ["ProtobufConversionUtils.scala"],
    visibility = ["//src/com/komanov/serialization/converters:__subpackages__"],
    deps = [
        ":protobuf_utils",
        "@com_google_protobuf_protobuf_java//jar",
    ],
)

scala_library(
    name = "reflection_utils",
    srcs = ["ReflectionUtils.scala"],
)

scala_library(
    name = "java_pb",
    srcs = ["JavaPbConverter.scala"],
    deps = [
        ":api",
        ":protobuf_conversion_utils",
        ":protobuf_utils",
        "//src/com/komanov/serialization/proto:protos_java",
    ],
)

scala_library(
    name = "scala_pb",
    srcs = ["ScalaPbConverter.scala"],
    deps = [
        ":api",
        ":protobuf_conversion_utils",
        ":protobuf_utils",
        ":reflection_utils",
        "//src/com/komanov/serialization/proto:protos_scala",
    ],
)

scala_library(
    name = "java_serialization",
    srcs = ["JavaSerializationConverter.scala"],
    deps = [
        ":api",
        "//src/com/komanov/serialization/io",
    ],
)

scala_library(
    name = "java_thrift",
    srcs = ["JavaThriftConverter.scala"],
    deps = [
        ":api",
        ":protobuf_utils",  # TODO FIX IT!!!
        "//src/com/komanov/serialization/domain:domain_java_thrift",
        "//third_party:org_apache_thrift_libthrift",
    ],
)

scala_library(
    name = "jackson",
    srcs = ["JsonConverter.scala"],
    deps = [
        ":api",
        "//third_party:com_fasterxml_jackson_core_jackson_core",
        "//third_party:com_fasterxml_jackson_core_jackson_databind",
        "//third_party:com_fasterxml_jackson_module_jackson_module_scala",
    ],
)

scala_library(
    name = "scrooge",
    srcs = ["ScroogeConverter.scala"],
    deps = [
        ":api",
        ":protobuf_utils",  # TODO FIX IT!!!
        ":reflection_utils",
        "//src/com/komanov/serialization/proto:thrift_scrooge",
    ],
)
