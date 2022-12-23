java_library(
    name = "jni",
    srcs = glob(["*.java"]),
    visibility = [
        "//src/com/komanov/jwt:__subpackages__",
    ],
    deps = [
        "//rs/base64:base64_lib",
        "//src/com/komanov/offheap/alloc",
        "@apangin_nalim_repo//:nalim",
    ],
)
