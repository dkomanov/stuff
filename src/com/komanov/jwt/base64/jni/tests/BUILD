scala_specs2_junit(
    name = "tests",
    srcs = glob(["*.scala"]),
    jvm_flags = [
        "-Djava.library.path=rs/base64",
    ],
    deps = [
        "//src/com/komanov/jwt/base64/jni",
    ],
)
