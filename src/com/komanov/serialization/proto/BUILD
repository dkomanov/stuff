java_proto_library(
    name = "protos_java",
    protos = [
        "events.proto",
        "site.proto",
    ],
    visibility = ["//src/com/komanov/serialization:__subpackages__"],
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

# We don't use these rules anywhere else. If we will -- move it to preluze_bazel.
load("@io_bazel_rules_scala//thrift:thrift.bzl", "thrift_library")
load("@io_bazel_rules_scala//twitter_scrooge:twitter_scrooge.bzl", "scrooge_scala_library")

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
    deps = [":thrift"],
)
