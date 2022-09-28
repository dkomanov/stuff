scala_library(
    name = "common",
    srcs = ["package.scala"],
    deps = [
        "//src/com/komanov/serialization/converters",
    ],
)

scala_binary(
    name = "report-generator",
    srcs = ["ReportGenerator.scala"],
    jvm_flags = [
        "--add-opens java.base/java.lang=ALL-UNNAMED",
        "--add-opens java.base/java.util=ALL-UNNAMED",
        "--add-opens java.base/java.lang.invoke=ALL-UNNAMED",
        "-Dfile.encoding=UTF-8",
    ],
    main_class = "com.komanov.serialization.bin.ReportGenerator",
    deps = [
        ":common",
        "//src/com/komanov/serialization/converters",
        "//src/com/komanov/serialization/domain/testdata",
        "//src/com/komanov/serialization/io",
    ],
)

scala_binary(
    name = "events-report-generator",
    srcs = ["EventsReportGenerator.scala"],
    jvm_flags = [
        "--add-opens java.base/java.lang=ALL-UNNAMED",
        "--add-opens java.base/java.util=ALL-UNNAMED",
        "--add-opens java.base/java.lang.invoke=ALL-UNNAMED",
        "-Dfile.encoding=UTF-8",
    ],
    main_class = "com.komanov.serialization.bin.EventsReportGenerator",
    deps = [
        ":common",
        "//src/com/komanov/serialization/converters",
        "//src/com/komanov/serialization/domain/testdata",
        "//src/com/komanov/serialization/io",
    ],
)
