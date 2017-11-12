scala_binary(
    name = "report-generator",
    srcs = ["ReportGenerator.scala"],
    main_class = "com.komanov.serialization.converters.bin.ReportGenerator",
    deps = [
        "//src/com/komanov/serialization/converters",
        "//src/com/komanov/serialization/domain/testdata",
    ],
)

scala_binary(
    name = "events-report-generator",
    srcs = ["EventsReportGenerator.scala"],
    main_class = "com.komanov.serialization.converters.bin.EventsReportGenerator",
    deps = [
        "//src/com/komanov/serialization/converters",
        "//src/com/komanov/serialization/domain/testdata",
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
    ("site-serialization-perf", "com.komanov.serialization.converters.bin.SiteSerializationPerfTestApp"),
    ("site-deserialization-perf", "com.komanov.serialization.converters.bin.SiteDeserializationPerfTestApp"),
    ("events-serialization-perf", "com.komanov.serialization.converters.bin.EventsSerializationPerfTestApp"),
    ("events-deserialization-perf", "com.komanov.serialization.converters.bin.EventsDeserializationPerfTestApp"),
]]
