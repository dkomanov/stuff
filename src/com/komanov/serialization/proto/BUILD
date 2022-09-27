load("@io_bazel_rules_scala//scala_proto:scala_proto.bzl", "scala_proto_library")
load("@io_bazel_rules_scala//thrift:thrift.bzl", "thrift_library")

proto_library(
    name = "protos",
    srcs = [
        "events.proto",
        "site.proto",
    ],
)

java_proto_library(
    name = "protos_java",
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    deps = [":protos"],
)

scala_proto_library(
    name = "protos_scala",
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    deps = [":protos"],
)

thrift_library(
    name = "thrift",
    srcs = [
        "events.thrift",
        "site.thrift",
    ],
)
