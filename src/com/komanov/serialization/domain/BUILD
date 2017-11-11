package(default_visibility = ["//src/com/komanov/serialization:__subpackages__"])

java_library(
    name = "domain_java_thrift",
    srcs = glob(["thrift/*.java"]),
    deps = [
        "//third_party:org_apache_thrift_libthrift",
        "//third_party:org_slf4j_slf4j_api",
        "//third_party:org_slf4j_slf4j_simple",
    ],
)

scala_library(
    name = "domain_scala_thrift",
    srcs = glob(["thriftscala/*.scala"]),
    deps = [
        "//third_party:com_twitter_scrooge_core_2_11",
        "//third_party:org_apache_thrift_libthrift",
    ],
)

scala_library(
    name = "domain",
    srcs = glob([
        "*.scala",
        "*.java",
    ]),
    exports = [
        "//third_party:com_fasterxml_jackson_core_jackson_annotations",
    ],
    deps = [
        "//third_party:com_fasterxml_jackson_core_jackson_annotations",
        "//third_party:org_scala_lang_modules_scala_pickling_2_11",
    ],
)
