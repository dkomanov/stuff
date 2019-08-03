package(default_visibility = ["//src/com/komanov/serialization:__subpackages__"])

java_library(
    name = "domain_java_thrift",
    srcs = glob(["thrift/*.java"]),
    deps = [
        "@scala_serialization_maven//:javax_annotation_javax_annotation_api",
        "@scala_serialization_maven//:org_apache_thrift_libthrift",
        "@scala_serialization_maven//:org_slf4j_slf4j_api",
    ],
)

scala_library(
    name = "domain",
    srcs = glob([
        "*.scala",
        "*.java",
    ]),
    exports = [
        "@scala_serialization_maven//:com_fasterxml_jackson_core_jackson_annotations",
    ],
    deps = [
        "@scala_serialization_maven//:com_fasterxml_jackson_core_jackson_annotations",
    ],
)
