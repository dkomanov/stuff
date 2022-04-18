scala_specs2_junit(
    name = "tests",
    srcs = glob(["*.scala"]),
    jvm_flags = [
        "--add-opens java.base/java.lang=ALL-UNNAMED",
    ],
    runtime_deps = [
        "@maven//:org_slf4j_slf4j_api",
    ],
    deps = [
        "//src/com/komanov/stringformat",
    ],
)
