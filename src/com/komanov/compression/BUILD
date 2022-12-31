java_library(
    name = "compression",
    srcs = glob(["*.java"]),
    visibility = ["//visibility:public"],
    runtime_deps = [
        "@compression//:com_aayushatharva_brotli4j_native_linux_x86_64",
        "@compression//:com_github_luben_zstd_jni",
    ],
    deps = [
        "@compression//:com_aayushatharva_brotli4j_brotli4j",
        "@compression//:org_apache_commons_commons_compress",
        "@compression//:org_apache_commons_commons_lang3",
        "@compression//:org_lz4_lz4_java",
        "@compression//:org_xerial_snappy_snappy_java",
    ],
)
