java_library(
    name = "native",
    srcs = ["Native.java"],
    deps = [
        "//rs/simple_jni_lib",
    ],
)

java_binary(
    name = "bin",
    srcs = ["Main.java"],
    main_class = "com.komanov.jni.rs.bin.Main",
    deps = [
        ":native",
    ],
)
