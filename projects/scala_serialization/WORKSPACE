workspace(name = "stuff")

# scala
rules_scala_version="24bc74b2664560fdba27b31da9e6c529dd231e1e"
http_archive(
    name = "io_bazel_rules_scala",
    strip_prefix = "rules_scala-%s" % rules_scala_version,
    type = "zip",
    url = "https://github.com/bazelbuild/rules_scala/archive/%s.zip" % rules_scala_version,
)

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")
scala_repositories()

load("@io_bazel_rules_scala//scala:toolchains.bzl", "scala_register_toolchains")
scala_register_toolchains()

load("@io_bazel_rules_scala//specs2:specs2_junit.bzl", "specs2_junit_repositories")
specs2_junit_repositories()

load("@io_bazel_rules_scala//jmh:jmh.bzl", "jmh_repositories")
jmh_repositories()

load("@io_bazel_rules_scala//scala_proto:scala_proto.bzl", "scala_proto_repositories")
scala_proto_repositories()

load("@io_bazel_rules_scala//twitter_scrooge:twitter_scrooge.bzl", "twitter_scrooge")
twitter_scrooge()

# rules_protobuf
git_repository(
  name = "org_pubref_rules_protobuf",
  remote = "https://github.com/pubref/rules_protobuf",
  tag = "v0.8.1",
)
load("@org_pubref_rules_protobuf//java:rules.bzl", "java_proto_repositories")
java_proto_repositories()

# Command to generate dependencies:
# ./generate_workspace.sh from bazel git repository
# https://docs.bazel.build/versions/master/generate-workspace.html
load("//:generate_workspace.bzl", "generated_maven_jars")
generated_maven_jars()
