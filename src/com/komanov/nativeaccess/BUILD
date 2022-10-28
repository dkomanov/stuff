java_library(
    name = "nativeaccess",
    srcs = glob(["*.java"]),
    visibility = [
        "//src/com/komanov/nativeaccess:__subpackages__",
    ],
    deps = [
        "//rs/getloadavg:getloadavg_lib",
        "//src/com/komanov/offheap/alloc",
        "@apangin_nalim_repo//:nalim",
        "@nativeaccess//:com_github_jnr_jnr_ffi",
        "@nativeaccess//:com_nativelibs4java_bridj",
        "@nativeaccess//:net_java_dev_jna_jna",
        "@nativeaccess//:net_java_dev_jna_jna_platform",
        "@nativeaccess//:org_bytedeco_javacpp_presets_systems",
        "@nativeaccess//:org_bytedeco_javacpp_presets_systems_platform",
    ],
)
