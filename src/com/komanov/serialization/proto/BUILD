# We don't use these rules anywhere else. If we will -- move it to prelude_bazel.
load("@io_bazel_rules_scala//scala_proto:scala_proto.bzl", "scalapb_proto_library")
load("@io_bazel_rules_scala//thrift:thrift.bzl", "thrift_library")
load("@io_bazel_rules_scala//twitter_scrooge:twitter_scrooge.bzl", "scrooge_scala_library")

java_proto_library(
    name = "protos_java",
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    deps = ["protos"],
)

proto_library(
    name = "protos",
    srcs = [
        "events.proto",
        "site.proto",
    ],
)

scalapb_proto_library(
    name = "protos_scala",
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    deps = ["protos"],
)

thrift_library(
    name = "thrift",
    srcs = [
        "events.thrift",
        "site.thrift",
    ],
)

scrooge_scala_library(
    name = "thrift_scrooge",
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
    deps = [
        ":thrift",
        "@scala_serialization_maven//:javax_annotation_javax_annotation_api",
    ],
)
