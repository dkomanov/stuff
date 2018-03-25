load("@io_bazel_rules_scala//scala:scala.bzl", "scala_specs2_junit_test")

def scala_specs2_junit(name,
                       srcs,
                       deps=[],
                       runtime_deps=[],
                       size="small"):
  scala_specs2_junit_test(
      name=name,
      srcs=srcs,
      deps=deps,
      runtime_deps=runtime_deps,
      size=size,
      suffixes=['Test'],
  )
