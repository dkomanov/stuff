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
