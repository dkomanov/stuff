load("@rules_rust//rust:defs.bzl", "rust_shared_library")

rust_shared_library(
    name = "rust_lib",
    srcs = [
        "getloadavg.rs",
    ],
    rustc_flags = ["--codegen=opt-level=3"],
    deps = [
        "@rs//:jni",
        "@rs//:libc",
    ],
)

cc_binary(
    name = "getloadavg_lib",
    linkshared = True,
    visibility = ["//src/com/komanov/nativeaccess:__subpackages__"],
    deps = [":rust_lib"],
)
