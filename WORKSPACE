workspace(name = "stuff")

scala_version = "2.12"
scala_full_version = "%s.10" % scala_version

rules_scala_version = "2aba2cd5499f2f926c903cb5ef7c023b8cb28508"

rules_jvm_external_tag = "3.2"
rules_jvm_external_sha = "82262ff4223c5fda6fb7ff8bd63db8131b51b413d26eb49e3131037e79e324af"

protobuf_version = "204f99488ce1ef74565239cf3963111ae4c774b7"
protobuf_version_sha256 = "98e76e0d31146bf878160db65b2ec2d98db333db8730573c4efec987da83c877"

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "io_bazel_rules_scala",
    sha256 = "f0c7b96e3e778cec41125501782734ea41ecb42f3e00c05786818438a434b4c7",
    strip_prefix = "rules_scala-%s" % rules_scala_version,
    type = "zip",
    url = "https://github.com/bazelbuild/rules_scala/archive/%s.zip" % rules_scala_version,
)

http_archive(
    name = "rules_jvm_external",
    sha256 = rules_jvm_external_sha,
    strip_prefix = "rules_jvm_external-%s" % rules_jvm_external_tag,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % rules_jvm_external_tag,
)

http_archive(
    name = "com_google_protobuf",
    sha256 = protobuf_version_sha256,
    strip_prefix = "protobuf-%s" % protobuf_version,
    url = "https://github.com/protocolbuffers/protobuf/archive/%s.tar.gz" % protobuf_version,
)

http_archive(
    name = "bazel_skylib",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-skylib/releases/download/1.0.2/bazel-skylib-1.0.2.tar.gz",
        "https://github.com/bazelbuild/bazel-skylib/releases/download/1.0.2/bazel-skylib-1.0.2.tar.gz",
    ],
    sha256 = "97e70364e9249702246c0e9444bccdc4b847bed1eb03c5a3ece4f83dfe6abc44",
)
load("@bazel_skylib//:workspace.bzl", "bazel_skylib_workspace")
bazel_skylib_workspace()

register_toolchains("//tools:default_scala_toolchain")

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories((
    scala_full_version,
    {
        "scala_compiler": "cedc3b9c39d215a9a3ffc0cc75a1d784b51e9edc7f13051a1b4ad5ae22cfbc0c",
        "scala_library": "0a57044d10895f8d3dd66ad4286891f607169d948845ac51e17b4c1cf0ab569d",
        "scala_reflect": "56b609e1bab9144fb51525bfa01ccd72028154fc40a58685a1e9adcbe7835730",
    },
))

load("@io_bazel_rules_scala//specs2:specs2_junit.bzl", "specs2_junit_repositories")
specs2_junit_repositories(scala_version = scala_full_version)

load("@io_bazel_rules_scala//jmh:jmh.bzl", "jmh_repositories")
jmh_repositories()

load("@io_bazel_rules_scala//scala_proto:scala_proto.bzl", "scala_proto_repositories")
scala_proto_repositories(scala_version = scala_full_version)

load("@io_bazel_rules_scala//scala_proto:toolchains.bzl", "scala_proto_register_toolchains")
scala_proto_register_toolchains()

load("@io_bazel_rules_scala//twitter_scrooge:twitter_scrooge.bzl", "twitter_scrooge")
twitter_scrooge(scala_version = scala_full_version)

load("@rules_jvm_external//:defs.bzl", "maven_install")
maven_repositories = ["https://repo1.maven.org/maven2"]

maven_install(
    artifacts = [
        "org.specs2:specs2-mock_%s:4.6.0" % scala_version,
        "org.slf4j:slf4j-api:1.7.25",
        "org.scala-lang.modules:scala-async_%s:0.10.0" % scala_version,
    ],
    fetch_sources = True,
    repositories = maven_repositories,
)

maven_install(
    name = "mysql_streaming_maven",
    artifacts = [
        "org.drizzle.jdbc:drizzle-jdbc:1.4",
        "org.mariadb.jdbc:mariadb-java-client:2.2.0",
        "mysql:mysql-connector-java:6.0.6",
        "com.wix:wix-embedded-mysql:4.2.0",
    ],
    fetch_sources = True,
    repositories = maven_repositories,
)

maven_install(
    name = "scala_serialization_maven",
    artifacts = [
        "org.slf4j:slf4j-api:1.7.25",
        "org.slf4j:slf4j-nop:1.7.25",
        "com.fasterxml.jackson.core:jackson-databind:2.9.9",
        "com.fasterxml.jackson.core:jackson-core:2.9.9",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:2.9.9",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-smile:2.9.9",
        "com.fasterxml.jackson.module:jackson-module-scala_%s:2.9.9" % scala_version,
        "io.suzaku:boopickle_%s:1.3.0" % scala_version,
        "com.twitter:chill_%s:0.9.2" % scala_version,
        "org.apache.thrift:libthrift:0.10.0",
        "io.circe:circe-core_%s:0.11.1" % scala_version,
        "io.circe:circe-generic_%s:0.11.1" % scala_version,
        "io.circe:circe-parser_%s:0.11.1" % scala_version,
        "com.github.plokhotnyuk.jsoniter-scala:jsoniter-scala-macros_%s:0.52.2" % scala_version,
        "com.google.protobuf:protobuf-java:3.10.0",
        "javax.annotation:javax.annotation-api:1.3.2",
        "com.evolutiongaming:kryo-macros_%s:1.1.0" % scala_version,
    ],
    fetch_sources = True,
    repositories = maven_repositories + ["https://dl.bintray.com/evolutiongaming/maven/"],
)
