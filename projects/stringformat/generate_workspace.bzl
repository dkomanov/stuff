# The following dependencies were calculated from:
#
# generate_workspace --repositories http://repo1.maven.org/maven2 --artifact=org.specs2:specs2-mock_2.11:3.8.8 --artifact=org.slf4j:slf4j-api:1.7.25


def generated_maven_jars():
  # org.specs2:specs2-common_2.11:jar:3.8.8
  # org.scalaz:scalaz-effect_2.11:bundle:7.2.7 got requested version
  native.maven_jar(
      name = "org_scalaz_scalaz_core_2_11",
      artifact = "org.scalaz:scalaz-core_2.11:7.2.7",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "ebf85118d0bf4ce18acebf1d8475ee7deb7f19f1",
  )


  native.maven_jar(
      name = "org_slf4j_slf4j_api",
      artifact = "org.slf4j:slf4j-api:1.7.25",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "da76ca59f6a57ee3102f8f9bd9cee742973efa8a",
  )


  # org.specs2:specs2-mock_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_specs2_specs2_core_2_11",
      artifact = "org.specs2:specs2-core_2.11:3.8.8",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "495bed00c73483f4f5f43945fde63c615d03e637",
  )


  # org.specs2:specs2-common_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_scala_lang_scala_reflect",
      artifact = "org.scala-lang:scala-reflect:2.11.8",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "b74530deeba742ab4f3134de0c2da0edc49ca361",
  )


  # org.specs2:specs2-mock_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_mockito_mockito_core",
      artifact = "org.mockito:mockito-core:1.9.5",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "c3264abeea62c4d2f367e21484fbb40c7e256393",
  )


  # org.specs2:specs2-core_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_specs2_specs2_matcher_2_11",
      artifact = "org.specs2:specs2-matcher_2.11:3.8.8",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "d2e967737abef7421e47b8994a8c92784e624d62",
  )


  # org.mockito:mockito-core:jar:1.9.5 wanted version 1.1
  # org.specs2:specs2-mock_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_hamcrest_hamcrest_core",
      artifact = "org.hamcrest:hamcrest-core:1.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "42a25dc3219429f0e5d060061f71acb49bf010a0",
  )


  # org.specs2:specs2-matcher_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_specs2_specs2_common_2_11",
      artifact = "org.specs2:specs2-common_2.11:3.8.8",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "15bc009eaae3a574796c0f558d8696b57ae903c3",
  )


  # org.specs2:specs2-common_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_scalaz_scalaz_effect_2_11",
      artifact = "org.scalaz:scalaz-effect_2.11:7.2.7",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "824bbb83da12224b3537c354c51eb3da72c435b5",
  )


  # org.specs2:specs2-common_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_scala_lang_modules_scala_parser_combinators_2_11",
      artifact = "org.scala-lang.modules:scala-parser-combinators_2.11:1.0.5",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "cbd78079c99262f7a535d12a00a2dc3da6a266a0",
  )


  # org.specs2:specs2-common_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_scala_lang_modules_scala_xml_2_11",
      artifact = "org.scala-lang.modules:scala-xml_2.11:1.0.5",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "77ac9be4033768cf03cc04fbd1fc5e5711de2459",
  )


  # org.specs2:specs2-core_2.11:jar:3.8.8 got requested version
  # org.specs2:specs2-matcher_2.11:jar:3.8.8 got requested version
  # org.scala-lang:scala-reflect:jar:2.11.8 got requested version
  # org.scalaz:scalaz-core_2.11:bundle:7.2.7 got requested version
  # org.scala-lang.modules:scala-xml_2.11:bundle:1.0.5 wanted version 2.11.7
  # org.specs2:specs2-common_2.11:jar:3.8.8 got requested version
  # org.specs2:specs2-mock_2.11:jar:3.8.8
  # org.scalaz:scalaz-effect_2.11:bundle:7.2.7 got requested version
  # org.scala-lang.modules:scala-parser-combinators_2.11:bundle:1.0.5 got requested version
  native.maven_jar(
      name = "org_scala_lang_scala_library",
      artifact = "org.scala-lang:scala-library:2.11.8",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "ddd5a8bced249bedd86fb4578a39b9fb71480573",
  )


  # org.mockito:mockito-core:jar:1.9.5
  native.maven_jar(
      name = "org_objenesis_objenesis",
      artifact = "org.objenesis:objenesis:1.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "9b473564e792c2bdf1449da1f0b1b5bff9805704",
  )


  native.maven_jar(
      name = "org_specs2_specs2_mock_2_11",
      artifact = "org.specs2:specs2-mock_2.11:3.8.8",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "d2eaee82df2da1516dbbd808249adbb822e705c6",
  )




def generated_java_libraries():
  native.java_library(
      name = "org_scalaz_scalaz_core_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_scalaz_scalaz_core_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "org_slf4j_slf4j_api",
      visibility = ["//visibility:public"],
      exports = ["@org_slf4j_slf4j_api//jar"],
  )


  native.java_library(
      name = "org_specs2_specs2_core_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_specs2_specs2_core_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_modules_scala_parser_combinators_2_11",
          ":org_scala_lang_modules_scala_xml_2_11",
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_scalaz_scalaz_core_2_11",
          ":org_scalaz_scalaz_effect_2_11",
          ":org_specs2_specs2_common_2_11",
          ":org_specs2_specs2_matcher_2_11",
      ],
  )


  native.java_library(
      name = "org_scala_lang_scala_reflect",
      visibility = ["//visibility:public"],
      exports = ["@org_scala_lang_scala_reflect//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "org_mockito_mockito_core",
      visibility = ["//visibility:public"],
      exports = ["@org_mockito_mockito_core//jar"],
      runtime_deps = [
          ":org_hamcrest_hamcrest_core",
          ":org_objenesis_objenesis",
      ],
  )


  native.java_library(
      name = "org_specs2_specs2_matcher_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_specs2_specs2_matcher_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_modules_scala_parser_combinators_2_11",
          ":org_scala_lang_modules_scala_xml_2_11",
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_scalaz_scalaz_core_2_11",
          ":org_scalaz_scalaz_effect_2_11",
          ":org_specs2_specs2_common_2_11",
      ],
  )


  native.java_library(
      name = "org_hamcrest_hamcrest_core",
      visibility = ["//visibility:public"],
      exports = ["@org_hamcrest_hamcrest_core//jar"],
  )


  native.java_library(
      name = "org_specs2_specs2_common_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_specs2_specs2_common_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_modules_scala_parser_combinators_2_11",
          ":org_scala_lang_modules_scala_xml_2_11",
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_scalaz_scalaz_core_2_11",
          ":org_scalaz_scalaz_effect_2_11",
      ],
  )


  native.java_library(
      name = "org_scalaz_scalaz_effect_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_scalaz_scalaz_effect_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_scalaz_scalaz_core_2_11",
      ],
  )


  native.java_library(
      name = "org_scala_lang_modules_scala_parser_combinators_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_scala_lang_modules_scala_parser_combinators_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "org_scala_lang_modules_scala_xml_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_scala_lang_modules_scala_xml_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "org_scala_lang_scala_library",
      visibility = ["//visibility:public"],
      exports = ["@org_scala_lang_scala_library//jar"],
  )


  native.java_library(
      name = "org_objenesis_objenesis",
      visibility = ["//visibility:public"],
      exports = ["@org_objenesis_objenesis//jar"],
  )


  native.java_library(
      name = "org_specs2_specs2_mock_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_specs2_specs2_mock_2_11//jar"],
      runtime_deps = [
          ":org_hamcrest_hamcrest_core",
          ":org_mockito_mockito_core",
          ":org_objenesis_objenesis",
          ":org_scala_lang_modules_scala_parser_combinators_2_11",
          ":org_scala_lang_modules_scala_xml_2_11",
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_scalaz_scalaz_core_2_11",
          ":org_scalaz_scalaz_effect_2_11",
          ":org_specs2_specs2_common_2_11",
          ":org_specs2_specs2_core_2_11",
          ":org_specs2_specs2_matcher_2_11",
      ],
  )


