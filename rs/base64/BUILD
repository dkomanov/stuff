load("@rules_rust//rust:defs.bzl", "rust_shared_library")

rust_shared_library(
    name = "rust_lib",
    srcs = [
        "base64.rs",
        "lib.rs",
        "nalim.rs",
    ],
    rustc_flags = ["--codegen=opt-level=3"],
    deps = [
        "@rs//:base64",
        "@rs//:base64-simd",
        "@rs//:jni",
    ],
)

cc_binary(
    name = "base64_lib",
    linkshared = True,
    visibility = ["//src/com/komanov/jwt:__subpackages__"],
    deps = [":rust_lib"],
)
