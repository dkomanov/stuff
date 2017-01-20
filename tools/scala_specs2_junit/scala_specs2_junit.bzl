load("@io_bazel_rules_scala//scala:scala.bzl", "scala_library")

def _jars_of_deps(deps):
  result = []
  for d in deps:
    result.append(_jar_of_dep(d))
  return result


def _jar_of_dep(dep):
  return "%s.jar" % Label(dep)


def scala_specs2_junit(name,
                       srcs,
                       deps=[],
                       deps_raw=[],
                       runtime_deps=[],
                       size="small",
                       test_class=None):

  specs2_deps = [
      "//third_party:org_specs2_specs2_junit_2_11",
      "//third_party:org_specs2_specs2_matcher_extra_2_11",
      "//third_party:org_specs2_specs2_mock_2_11",
      ]

  test_library_name = name + "-library"
  test_library_import_name = test_library_name + "-import"

  scala_library(
      name = test_library_name,
      srcs = srcs,
      deps = deps + deps_raw + specs2_deps,
  )

  native.java_import(
      name = test_library_import_name,
      jars = _jars_of_deps(deps) + [
        ":" + test_library_name + ".jar",
      ],
      runtime_deps = deps_raw + runtime_deps + specs2_deps,
  )

  native.java_test(
      name = name,
      size = size,
      test_class = test_class,
      runtime_deps = [
          ":" + test_library_import_name,
      ],
  )
