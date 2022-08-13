scala_library(
    name = "offheap",
    srcs = glob([
        "*.scala",
        "*.java",
    ]),
    visibility = [
        "//src/com/komanov/offheap:__subpackages__",
    ],
    deps = [
        "@offheap_maven//:io_netty_netty5_buffer",
        "@offheap_maven//:io_netty_netty5_common",
        "@offheap_maven//:io_netty_netty_buffer",
        "@offheap_maven//:io_netty_netty_common",
    ],
)
