scala_specs2_junit(
    name = "tests",
    srcs = glob(["*.scala"]),
    jvm_flags = [
        "-Djava.library.path=rs/base64:bazel-bin/rs/base64:/home/dkomanov/src/dkomanov/rust-stuff/target/release",  # /home/dkomanov/src/dkomanov/rust-stuff/target/release
        "-XX:+UnlockExperimentalVMOptions",
        "-XX:+EnableJVMCI",
        "--add-exports",
        "jdk.internal.vm.ci/jdk.vm.ci.code=ALL-UNNAMED",
        "--add-exports",
        "jdk.internal.vm.ci/jdk.vm.ci.code.site=ALL-UNNAMED",
        "--add-exports",
        "jdk.internal.vm.ci/jdk.vm.ci.hotspot=ALL-UNNAMED",
        "--add-exports",
        "jdk.internal.vm.ci/jdk.vm.ci.meta=ALL-UNNAMED",
        "--add-exports",
        "jdk.internal.vm.ci/jdk.vm.ci.runtime=ALL-UNNAMED",
    ],
    deps = [
        "//src/com/komanov/jwt/base64/jni",
    ],
)
