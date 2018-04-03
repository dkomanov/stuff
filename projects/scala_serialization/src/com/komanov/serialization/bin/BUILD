scala_binary(
    name = "report-generator",
    srcs = ["ReportGenerator.scala"],
    main_class = "com.komanov.serialization.bin.ReportGenerator",
    deps = [
        "//src/com/komanov/serialization/converters",
        "//src/com/komanov/serialization/domain/testdata",
        "//src/com/komanov/serialization/io",
    ],
)

scala_binary(
    name = "events-report-generator",
    srcs = ["EventsReportGenerator.scala"],
    main_class = "com.komanov.serialization.bin.EventsReportGenerator",
    deps = [
        "//src/com/komanov/serialization/converters",
        "//src/com/komanov/serialization/domain/testdata",
        "//src/com/komanov/serialization/io",
    ],
)

[scala_binary(
    name = name,
    srcs = ["BasePerfTestApp.scala"],
    main_class = main_class,
    deps = [
        "//src/com/komanov/serialization/converters",
        "//src/com/komanov/serialization/domain",
        "//src/com/komanov/serialization/domain/testdata",
    ],
) for name, main_class in [
    ("site-serialization-perf", "com.komanov.serialization.bin.SiteSerializationPerfTestApp"),
    ("site-deserialization-perf", "com.komanov.serialization.bin.SiteDeserializationPerfTestApp"),
    ("events-serialization-perf", "com.komanov.serialization.bin.EventsSerializationPerfTestApp"),
    ("events-deserialization-perf", "com.komanov.serialization.bin.EventsDeserializationPerfTestApp"),
]]
