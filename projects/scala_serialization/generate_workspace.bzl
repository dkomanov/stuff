# The following dependencies were calculated from:
#
# generate_workspace --repositories http://repo1.maven.org/maven2 --artifact=org.slf4j:slf4j-api:1.7.25 --artifact=org.slf4j:slf4j-nop:1.7.25 --artifact=com.fasterxml.jackson.core:jackson-databind:2.9.4 --artifact=com.fasterxml.jackson.core:jackson-core:2.9.4 --artifact=com.fasterxml.jackson.module:jackson-module-scala_2.11:2.9.4 --artifact=org.scala-lang.modules:scala-pickling_2.11:0.11.0-M2 --artifact=me.chrons:boopickle_2.11:1.2.5 --artifact=com.twitter:chill_2.11:0.9.2 --artifact=org.apache.thrift:libthrift:0.10.0 --artifact=io.circe:circe-core_2.11:0.9.2 --artifact=io.circe:circe-generic_2.11:0.9.2 --artifact=io.circe:circe-parser_2.11:0.9.2


def generated_maven_jars():
  # org.typelevel:machinist_2.11:jar:0.6.2 wanted version 2.11.8
  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.4
  # org.scala-lang:scala-compiler:jar:2.11.7 wanted version 2.11.7
  # org.scala-lang.modules:scala-pickling_2.11:jar:0.11.0-M2 wanted version 2.11.7
  native.maven_jar(
      name = "org_scala_lang_scala_reflect",
      artifact = "org.scala-lang:scala-reflect:2.11.11",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "2addc7e09cf2e77e2243a5772bd0430c32c2b410",
  )


  # org.apache.httpcomponents:httpclient:jar:4.4.1
  # org.apache.thrift:libthrift:pom:0.10.0 got requested version
  native.maven_jar(
      name = "org_apache_httpcomponents_httpcore",
      artifact = "org.apache.httpcomponents:httpcore:4.4.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "f5aa318bda4c6c8d688c9d00b90681dcd82ce636",
  )


  # org.typelevel:cats-macros_2.11:jar:1.0.1
  # org.typelevel:cats-core_2.11:jar:1.0.1 got requested version
  native.maven_jar(
      name = "org_typelevel_machinist_2_11",
      artifact = "org.typelevel:machinist_2.11:0.6.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "029c6a46d66b6616f8795a70753e6753975f42fc",
  )


  # io.circe:circe-generic_2.11:jar:0.9.2
  # com.chuusai:shapeless_2.11:bundle:2.3.3 got requested version
  native.maven_jar(
      name = "org_typelevel_macro_compat_2_11",
      artifact = "org.typelevel:macro-compat_2.11:1.1.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "0cb87cb74fd5fb118fede3f98075c2044616b35d",
  )


  # com.twitter:chill-java:jar:0.9.2
  # com.twitter:chill_2.11:jar:0.9.2 got requested version
  native.maven_jar(
      name = "com_esotericsoftware_kryo_shaded",
      artifact = "com.esotericsoftware:kryo-shaded:4.0.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "4ae3bacfaea6459d8d63c6cf17c3718422fb2def",
  )


  # org.apache.httpcomponents:httpclient:jar:4.4.1
  native.maven_jar(
      name = "commons_codec_commons_codec",
      artifact = "commons-codec:commons-codec:1.9",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "9ce04e34240f674bc72680f8b843b1457383161a",
  )


  # org.scala-lang.modules:scala-pickling_2.11:jar:0.11.0-M2
  native.maven_jar(
      name = "org_scala_lang_scala_compiler",
      artifact = "org.scala-lang:scala-compiler:2.11.7",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "1454c21d39a4d991006a2a47c164f675ea1dafaf",
  )


  # io.circe:circe-jawn_2.11:jar:0.9.2 got requested version
  # io.circe:circe-generic_2.11:jar:0.9.2 got requested version
  # io.circe:circe-parser_2.11:jar:0.9.2 got requested version
  native.maven_jar(
      name = "io_circe_circe_core_2_11",
      artifact = "io.circe:circe-core_2.11:0.9.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "c4af46d9c2b55588704310d9aca1121f57684584",
  )


  # com.fasterxml.jackson.module:jackson-module-paranamer:bundle:2.9.4
  native.maven_jar(
      name = "com_thoughtworks_paranamer_paranamer",
      artifact = "com.thoughtworks.paranamer:paranamer:2.8",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "619eba74c19ccf1da8ebec97a2d7f8ba05773dd6",
  )


  native.maven_jar(
      name = "org_apache_thrift_libthrift",
      artifact = "org.apache.thrift:libthrift:0.10.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "3201c5a6d85d3f030bae5a520abaaf81ef7df037",
  )


  # org.typelevel:cats-core_2.11:jar:1.0.1
  native.maven_jar(
      name = "org_typelevel_cats_macros_2_11",
      artifact = "org.typelevel:cats-macros_2.11:1.0.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "f5bc0a113ed95a451f6c31ae597a798fbddb76b5",
  )


  # io.circe:circe-generic_2.11:jar:0.9.2
  native.maven_jar(
      name = "com_chuusai_shapeless_2_11",
      artifact = "com.chuusai:shapeless_2.11:2.3.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "ea34d4b6128b9090386945dcb952816bd9e87ce2",
  )


  # org.typelevel:cats-core_2.11:jar:1.0.1
  native.maven_jar(
      name = "org_typelevel_cats_kernel_2_11",
      artifact = "org.typelevel:cats-kernel_2.11:1.0.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "029d1039d964b894389c09d3dfa1e1eb6cdfccd7",
  )


  # io.circe:circe-core_2.11:jar:0.9.2
  native.maven_jar(
      name = "org_typelevel_cats_core_2_11",
      artifact = "org.typelevel:cats-core_2.11:1.0.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "c796592c733267960bd620fba97053685eb1333c",
  )


  native.maven_jar(
      name = "org_slf4j_slf4j_nop",
      artifact = "org.slf4j:slf4j-nop:1.7.25",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "8c7708c79afec923de8957b7d4f90177628b9fcd",
  )


  # com.esotericsoftware:kryo-shaded:bundle:4.0.0
  native.maven_jar(
      name = "org_objenesis_objenesis",
      artifact = "org.objenesis:objenesis:2.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "3fb533efdaa50a768c394aa4624144cf8df17845",
  )


  # io.circe:circe-core_2.11:jar:0.9.2
  native.maven_jar(
      name = "io_circe_circe_numbers_2_11",
      artifact = "io.circe:circe-numbers_2.11:0.9.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "b37596a648e14bbddbcf1f37a2bf373715e4e12a",
  )


  native.maven_jar(
      name = "me_chrons_boopickle_2_11",
      artifact = "me.chrons:boopickle_2.11:1.2.5",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "7b952022f2a8a87acf054c3571360ee19955542c",
  )


  native.maven_jar(
      name = "com_fasterxml_jackson_module_jackson_module_scala_2_11",
      artifact = "com.fasterxml.jackson.module:jackson-module-scala_2.11:2.9.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "c4c60acf9dc4776f118b68cbadd2c0e3f49ff0f3",
  )


  # org.slf4j:slf4j-nop:jar:1.7.25 got requested version
  # org.apache.thrift:libthrift:pom:0.10.0 wanted version 1.7.12
  native.maven_jar(
      name = "org_slf4j_slf4j_api",
      artifact = "org.slf4j:slf4j-api:1.7.25",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "da76ca59f6a57ee3102f8f9bd9cee742973efa8a",
  )


  # org.apache.httpcomponents:httpclient:jar:4.4.1
  native.maven_jar(
      name = "commons_logging_commons_logging",
      artifact = "commons-logging:commons-logging:1.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "4bfc12adfe4842bf07b657f0369c4cb522955686",
  )


  native.maven_jar(
      name = "com_twitter_chill_2_11",
      artifact = "com.twitter:chill_2.11:0.9.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "b8080797c5a7c07e8f00a9f6019266d05dce0cb2",
  )


  # com.esotericsoftware:kryo-shaded:bundle:4.0.0
  native.maven_jar(
      name = "com_esotericsoftware_minlog",
      artifact = "com.esotericsoftware:minlog:1.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "8d2b87348c82b82e69ac2039ddbbc9d36dc69c9a",
  )


  # io.circe:circe-jawn_2.11:jar:0.9.2
  native.maven_jar(
      name = "org_spire_math_jawn_parser_2_11",
      artifact = "org.spire-math:jawn-parser_2.11:0.11.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "ad6bd066b122f514a040e6a7e24f8448e309e41d",
  )


  # com.twitter:chill_2.11:jar:0.9.2
  native.maven_jar(
      name = "com_twitter_chill_java",
      artifact = "com.twitter:chill-java:0.9.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "2eaacafa060bd70a49066dd806f9958a844c6be4",
  )


  # io.circe:circe-parser_2.11:jar:0.9.2
  native.maven_jar(
      name = "io_circe_circe_jawn_2_11",
      artifact = "io.circe:circe-jawn_2.11:0.9.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "d62ddd1ed99d567e1c395ac3e4fc9b53ceeccdc2",
  )


  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.4
  native.maven_jar(
      name = "com_fasterxml_jackson_module_jackson_module_paranamer",
      artifact = "com.fasterxml.jackson.module:jackson-module-paranamer:2.9.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "899b339804d5f9f797e8cca8cf235e0e769fa630",
  )


  # com.fasterxml.jackson.core:jackson-databind:bundle:2.9.4
  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.4 wanted version 2.9.4
  native.maven_jar(
      name = "com_fasterxml_jackson_core_jackson_annotations",
      artifact = "com.fasterxml.jackson.core:jackson-annotations:2.9.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "07c10d545325e3a6e72e06381afe469fd40eb701",
  )


  # org.scala-lang.modules:scala-pickling_2.11:jar:0.11.0-M2 wanted version 1.0.2
  # org.scala-lang:scala-compiler:jar:2.11.7
  native.maven_jar(
      name = "org_scala_lang_modules_scala_parser_combinators_2_11",
      artifact = "org.scala-lang.modules:scala-parser-combinators_2.11:1.0.5",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "cbd78079c99262f7a535d12a00a2dc3da6a266a0",
  )


  # org.apache.thrift:libthrift:pom:0.10.0
  native.maven_jar(
      name = "org_apache_httpcomponents_httpclient",
      artifact = "org.apache.httpcomponents:httpclient:4.4.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "016d0bc512222f1253ee6b64d389c84e22f697f0",
  )


  native.maven_jar(
      name = "io_circe_circe_generic_2_11",
      artifact = "io.circe:circe-generic_2.11:0.9.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "d988edf6a4e1d05bb3710ee4bfd6f2c2b6655888",
  )


  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.4 got requested version
  # com.fasterxml.jackson.module:jackson-module-paranamer:bundle:2.9.4 got requested version
  native.maven_jar(
      name = "com_fasterxml_jackson_core_jackson_databind",
      artifact = "com.fasterxml.jackson.core:jackson-databind:2.9.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "498bbc3b94f566982c7f7c6d4d303fce365529be",
  )


  # org.scala-lang:scala-compiler:jar:2.11.7
  native.maven_jar(
      name = "org_scala_lang_modules_scala_xml_2_11",
      artifact = "org.scala-lang.modules:scala-xml_2.11:1.0.5",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "77ac9be4033768cf03cc04fbd1fc5e5711de2459",
  )


  # org.scala-lang.modules:scala-parser-combinators_2.11:bundle:1.0.5 wanted version 2.11.8
  # org.typelevel:machinist_2.11:jar:0.6.2 wanted version 2.11.8
  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.4
  # org.typelevel:cats-core_2.11:jar:1.0.1 wanted version 2.11.12
  # io.circe:circe-generic_2.11:jar:0.9.2 wanted version 2.11.12
  # org.spire-math:jawn-parser_2.11:jar:0.11.1 got requested version
  # io.circe:circe-numbers_2.11:jar:0.9.2 wanted version 2.11.12
  # org.typelevel:cats-kernel_2.11:jar:1.0.1 wanted version 2.11.12
  # io.circe:circe-core_2.11:jar:0.9.2 wanted version 2.11.12
  # io.circe:circe-parser_2.11:jar:0.9.2 wanted version 2.11.12
  # org.scala-lang.modules:scala-xml_2.11:bundle:1.0.5 wanted version 2.11.7
  # me.chrons:boopickle_2.11:jar:1.2.5 wanted version 2.11.8
  # org.scala-lang:scala-reflect:jar:2.11.11 got requested version
  # org.scala-lang:scala-compiler:jar:2.11.7 wanted version 2.11.7
  # org.scala-lang.modules:scala-pickling_2.11:jar:0.11.0-M2 wanted version 2.11.7
  # org.typelevel:cats-macros_2.11:jar:1.0.1 wanted version 2.11.12
  # io.circe:circe-jawn_2.11:jar:0.9.2 wanted version 2.11.12
  # com.chuusai:shapeless_2.11:bundle:2.3.3 wanted version 2.11.12
  # org.typelevel:macro-compat_2.11:jar:1.1.1 wanted version 2.11.7
  # com.twitter:chill_2.11:jar:0.9.2 wanted version 2.11.8
  native.maven_jar(
      name = "org_scala_lang_scala_library",
      artifact = "org.scala-lang:scala-library:2.11.11",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "e283d2b7fde6504f6a86458b1f6af465353907cc",
  )


  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.4 got requested version
  native.maven_jar(
      name = "com_fasterxml_jackson_core_jackson_core",
      artifact = "com.fasterxml.jackson.core:jackson-core:2.9.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "a9a71ec1aa37da47db168fede9a4a5fb5e374320",
  )


  native.maven_jar(
      name = "io_circe_circe_parser_2_11",
      artifact = "io.circe:circe-parser_2.11:0.9.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "7062e12f2517b2adc9640d25325f044fee4dc670",
  )


  native.maven_jar(
      name = "org_scala_lang_modules_scala_pickling_2_11",
      artifact = "org.scala-lang.modules:scala-pickling_2.11:0.11.0-M2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "962574752f42318359ce3ecb010d85e6f39a3c7b",
  )




def generated_java_libraries():
  native.java_library(
      name = "org_scala_lang_scala_reflect",
      visibility = ["//visibility:public"],
      exports = ["@org_scala_lang_scala_reflect//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "org_apache_httpcomponents_httpcore",
      visibility = ["//visibility:public"],
      exports = ["@org_apache_httpcomponents_httpcore//jar"],
  )


  native.java_library(
      name = "org_typelevel_machinist_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_machinist_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
      ],
  )


  native.java_library(
      name = "org_typelevel_macro_compat_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_macro_compat_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "com_esotericsoftware_kryo_shaded",
      visibility = ["//visibility:public"],
      exports = ["@com_esotericsoftware_kryo_shaded//jar"],
      runtime_deps = [
          ":com_esotericsoftware_minlog",
          ":org_objenesis_objenesis",
      ],
  )


  native.java_library(
      name = "commons_codec_commons_codec",
      visibility = ["//visibility:public"],
      exports = ["@commons_codec_commons_codec//jar"],
  )


  native.java_library(
      name = "org_scala_lang_scala_compiler",
      visibility = ["//visibility:public"],
      exports = ["@org_scala_lang_scala_compiler//jar"],
      runtime_deps = [
          ":org_scala_lang_modules_scala_parser_combinators_2_11",
          ":org_scala_lang_modules_scala_xml_2_11",
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
      ],
  )


  native.java_library(
      name = "io_circe_circe_core_2_11",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_core_2_11//jar"],
      runtime_deps = [
          ":io_circe_circe_numbers_2_11",
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_typelevel_cats_core_2_11",
          ":org_typelevel_cats_kernel_2_11",
          ":org_typelevel_cats_macros_2_11",
          ":org_typelevel_machinist_2_11",
      ],
  )


  native.java_library(
      name = "com_thoughtworks_paranamer_paranamer",
      visibility = ["//visibility:public"],
      exports = ["@com_thoughtworks_paranamer_paranamer//jar"],
  )


  native.java_library(
      name = "org_apache_thrift_libthrift",
      visibility = ["//visibility:public"],
      exports = ["@org_apache_thrift_libthrift//jar"],
      runtime_deps = [
          ":commons_codec_commons_codec",
          ":commons_logging_commons_logging",
          ":org_apache_httpcomponents_httpclient",
          ":org_apache_httpcomponents_httpcore",
          ":org_slf4j_slf4j_api",
      ],
  )


  native.java_library(
      name = "org_typelevel_cats_macros_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_cats_macros_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_typelevel_machinist_2_11",
      ],
  )


  native.java_library(
      name = "com_chuusai_shapeless_2_11",
      visibility = ["//visibility:public"],
      exports = ["@com_chuusai_shapeless_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_typelevel_macro_compat_2_11",
      ],
  )


  native.java_library(
      name = "org_typelevel_cats_kernel_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_cats_kernel_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "org_typelevel_cats_core_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_cats_core_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_typelevel_cats_kernel_2_11",
          ":org_typelevel_cats_macros_2_11",
          ":org_typelevel_machinist_2_11",
      ],
  )


  native.java_library(
      name = "org_slf4j_slf4j_nop",
      visibility = ["//visibility:public"],
      exports = ["@org_slf4j_slf4j_nop//jar"],
      runtime_deps = [
          ":org_slf4j_slf4j_api",
      ],
  )


  native.java_library(
      name = "org_objenesis_objenesis",
      visibility = ["//visibility:public"],
      exports = ["@org_objenesis_objenesis//jar"],
  )


  native.java_library(
      name = "io_circe_circe_numbers_2_11",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_numbers_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "me_chrons_boopickle_2_11",
      visibility = ["//visibility:public"],
      exports = ["@me_chrons_boopickle_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "com_fasterxml_jackson_module_jackson_module_scala_2_11",
      visibility = ["//visibility:public"],
      exports = ["@com_fasterxml_jackson_module_jackson_module_scala_2_11//jar"],
      runtime_deps = [
          ":com_fasterxml_jackson_core_jackson_annotations",
          ":com_fasterxml_jackson_core_jackson_core",
          ":com_fasterxml_jackson_core_jackson_databind",
          ":com_fasterxml_jackson_module_jackson_module_paranamer",
          ":com_thoughtworks_paranamer_paranamer",
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
      ],
  )


  native.java_library(
      name = "org_slf4j_slf4j_api",
      visibility = ["//visibility:public"],
      exports = ["@org_slf4j_slf4j_api//jar"],
  )


  native.java_library(
      name = "commons_logging_commons_logging",
      visibility = ["//visibility:public"],
      exports = ["@commons_logging_commons_logging//jar"],
  )


  native.java_library(
      name = "com_twitter_chill_2_11",
      visibility = ["//visibility:public"],
      exports = ["@com_twitter_chill_2_11//jar"],
      runtime_deps = [
          ":com_esotericsoftware_kryo_shaded",
          ":com_esotericsoftware_minlog",
          ":com_twitter_chill_java",
          ":org_objenesis_objenesis",
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "com_esotericsoftware_minlog",
      visibility = ["//visibility:public"],
      exports = ["@com_esotericsoftware_minlog//jar"],
  )


  native.java_library(
      name = "org_spire_math_jawn_parser_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_spire_math_jawn_parser_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "com_twitter_chill_java",
      visibility = ["//visibility:public"],
      exports = ["@com_twitter_chill_java//jar"],
      runtime_deps = [
          ":com_esotericsoftware_kryo_shaded",
          ":com_esotericsoftware_minlog",
          ":org_objenesis_objenesis",
      ],
  )


  native.java_library(
      name = "io_circe_circe_jawn_2_11",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_jawn_2_11//jar"],
      runtime_deps = [
          ":io_circe_circe_core_2_11",
          ":org_scala_lang_scala_library",
          ":org_spire_math_jawn_parser_2_11",
      ],
  )


  native.java_library(
      name = "com_fasterxml_jackson_module_jackson_module_paranamer",
      visibility = ["//visibility:public"],
      exports = ["@com_fasterxml_jackson_module_jackson_module_paranamer//jar"],
      runtime_deps = [
          ":com_fasterxml_jackson_core_jackson_databind",
          ":com_thoughtworks_paranamer_paranamer",
      ],
  )


  native.java_library(
      name = "com_fasterxml_jackson_core_jackson_annotations",
      visibility = ["//visibility:public"],
      exports = ["@com_fasterxml_jackson_core_jackson_annotations//jar"],
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
      name = "org_apache_httpcomponents_httpclient",
      visibility = ["//visibility:public"],
      exports = ["@org_apache_httpcomponents_httpclient//jar"],
      runtime_deps = [
          ":commons_codec_commons_codec",
          ":commons_logging_commons_logging",
          ":org_apache_httpcomponents_httpcore",
      ],
  )


  native.java_library(
      name = "io_circe_circe_generic_2_11",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_generic_2_11//jar"],
      runtime_deps = [
          ":com_chuusai_shapeless_2_11",
          ":io_circe_circe_core_2_11",
          ":org_scala_lang_scala_library",
          ":org_typelevel_macro_compat_2_11",
      ],
  )


  native.java_library(
      name = "com_fasterxml_jackson_core_jackson_databind",
      visibility = ["//visibility:public"],
      exports = ["@com_fasterxml_jackson_core_jackson_databind//jar"],
      runtime_deps = [
          ":com_fasterxml_jackson_core_jackson_annotations",
          ":com_fasterxml_jackson_core_jackson_core",
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
      name = "com_fasterxml_jackson_core_jackson_core",
      visibility = ["//visibility:public"],
      exports = ["@com_fasterxml_jackson_core_jackson_core//jar"],
  )


  native.java_library(
      name = "io_circe_circe_parser_2_11",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_parser_2_11//jar"],
      runtime_deps = [
          ":io_circe_circe_core_2_11",
          ":io_circe_circe_jawn_2_11",
          ":org_scala_lang_scala_library",
          ":org_spire_math_jawn_parser_2_11",
      ],
  )


  native.java_library(
      name = "org_scala_lang_modules_scala_pickling_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_scala_lang_modules_scala_pickling_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_modules_scala_parser_combinators_2_11",
          ":org_scala_lang_modules_scala_xml_2_11",
          ":org_scala_lang_scala_compiler",
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
      ],
  )


