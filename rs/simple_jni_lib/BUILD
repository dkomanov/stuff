load("@rules_rust//rust:defs.bzl", "rust_shared_library")

rust_shared_library(
    name = "rust_lib",
    srcs = [
        "simplejni.rs",
    ],
    deps = [
        "@rs//:jni",
    ],
)

# This is a workaround. `rust_shared_library` doesn't work by itself, need to wrap in something that does work:
cc_binary(
    name = "simple_jni_lib",
    linkshared = True,
    visibility = ["//src/com/komanov/jni/rs/bin:__pkg__"],
    deps = [":rust_lib"],
)
