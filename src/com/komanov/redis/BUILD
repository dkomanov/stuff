package(default_visibility = ["//src/com/komanov/redis:__subpackages__"])

scala_library(
    name = "redis",
    srcs = glob([
        "*.scala",
        "*.java",
    ]),
    deps = [
        "@offheap_maven//:io_lettuce_lettuce_core",
    ],
)
