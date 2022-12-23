scala_benchmark_jmh(
    name = "jmh",
    srcs = ["Base64Benchmarks.scala"],
    deps = [
        ":lib",
        ":panama",
        "//src/com/komanov/jwt/base64",
        "//src/com/komanov/jwt/base64/jmh:jmh_base",
    ],
)

java_library(
    name = "lib",
    srcs = [
        "NativeAccessor.java",
    ],
    visibility = ["//src/com/komanov/jwt/base64:__subpackages__"],
)

java_library(
    name = "panama",
    srcs = [
        "PanamaJdk17NativeAccessor.java",
        "module-info.java",
    ],
    javacopts = [
        #"--enable-native-access=ALL-UNNAMED",
        #"--add-modules=jdk.incubator.foreign",
        "--enable-preview",
    ],
    visibility = ["//src/com/komanov/jwt/base64:__subpackages__"],
    deps = [
        #":lib",
    ],
)
