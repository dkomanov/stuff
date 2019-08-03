load("@io_bazel_rules_scala//scala:scala_toolchain.bzl", "scala_toolchain")

scala_toolchain(
    name = "default_scala_toolchain_impl",
    scalacopts = [
        #"-Ywarn-unused",
        "-deprecation",
        "-explaintypes",
    ],
    visibility = ["//visibility:public"],
)

toolchain(
    name = "default_scala_toolchain",
    toolchain = "default_scala_toolchain_impl",
    toolchain_type = "@io_bazel_rules_scala//scala:toolchain_type",
    visibility = ["//visibility:public"],
)
