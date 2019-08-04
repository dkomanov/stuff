workspace(name = "stuff")

scala_version = "2.12"

scala_full_version = "%s.8" % scala_version

rules_scala_version = "acac888c86e79110d1d08ab5578a7d0101c97963"

rules_jvm_external_tag = "2.5"

rules_jvm_external_sha = "249e8129914be6d987ca57754516be35a14ea866c616041ff0cd32ea94d2f3a1"

protobuf_version = "09745575a923640154bcf307fba8aedff47f240a"

protobuf_version_sha256 = "416212e14481cff8fd4849b1c1c1200a7f34808a54377e22d7447efdf54ad758"

skylib_version = "0.9.0"

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "io_bazel_rules_scala",
    sha256 = "b58e588152b3d42ef2a274e7211bec89ccc134b920861277c87f644fe746cbed",
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
    sha256 = "1dde365491125a3db70731e25658dfdd3bc5dbdfd11b840b3e987ecf043c7ca0",
    type = "tar.gz",
    url = "https://github.com/bazelbuild/bazel-skylib/releases/download/{}/bazel_skylib-{}.tar.gz".format(skylib_version, skylib_version),
)

register_toolchains("//tools:default_scala_toolchain")

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")

scala_repositories((
    scala_full_version,
    {
        "scala_compiler": "f34e9119f45abd41e85b9e121ba19dd9288b3b4af7f7047e86dc70236708d170",
        "scala_library": "321fb55685635c931eba4bc0d7668349da3f2c09aee2de93a70566066ff25c28",
        "scala_reflect": "4d6405395c4599ce04cea08ba082339e3e42135de9aae2923c9f5367e957315a",
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
        "com.google.protobuf:protobuf-java:3.9.0",
        "javax.annotation:javax.annotation-api:1.3.2",
        "com.evolutiongaming:kryo-macros_%s:1.1.0" % scala_version,
    ],
    fetch_sources = True,
    repositories = maven_repositories + ["https://dl.bintray.com/evolutiongaming/maven/"],
)
