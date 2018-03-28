# The following dependencies were calculated from:
#
# generate_workspace --repositories http://repo1.maven.org/maven2 --artifact=org.slf4j:slf4j-api:1.7.25 --artifact=org.slf4j:slf4j-nop:1.7.25 --artifact=com.fasterxml.jackson.core:jackson-databind:2.9.4 --artifact=com.fasterxml.jackson.core:jackson-core:2.9.4 --artifact=com.fasterxml.jackson.module:jackson-module-scala_2.12:2.9.4 --artifact=io.suzaku:boopickle_2.12:1.3.0 --artifact=com.twitter:chill_2.12:0.9.2 --artifact=org.apache.thrift:libthrift:0.10.0 --artifact=io.circe:circe-core_2.12:0.9.3 --artifact=io.circe:circe-generic_2.12:0.9.3 --artifact=io.circe:circe-parser_2.12:0.9.3


def generated_maven_jars():
  # com.fasterxml.jackson.module:jackson-module-scala_2.12:bundle:2.9.4
  # org.typelevel:machinist_2.12:jar:0.6.2 wanted version 2.12.0
  native.maven_jar(
      name = "org_scala_lang_scala_reflect",
      artifact = "org.scala-lang:scala-reflect:2.12.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "2df9e6015b97e35464edddd20eec392bb54fab11",
  )


  # org.apache.httpcomponents:httpclient:jar:4.4.1
  # org.apache.thrift:libthrift:pom:0.10.0 got requested version
  native.maven_jar(
      name = "org_apache_httpcomponents_httpcore",
      artifact = "org.apache.httpcomponents:httpcore:4.4.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "f5aa318bda4c6c8d688c9d00b90681dcd82ce636",
  )


  # org.typelevel:cats-macros_2.12:jar:1.0.1
  # org.typelevel:cats-core_2.12:jar:1.0.1 got requested version
  native.maven_jar(
      name = "org_typelevel_machinist",
      artifact = "org.typelevel:machinist_2.12:0.6.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "a0e8521deafd0d24c18460104eee6ce4c679c779",
  )


  # com.chuusai:shapeless_2.12:bundle:2.3.3 got requested version
  # io.circe:circe-generic_2.12:jar:0.9.3
  native.maven_jar(
      name = "org_typelevel_macro_compat",
      artifact = "org.typelevel:macro-compat_2.12:1.1.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "ed809d26ef4237d7c079ae6cf7ebd0dfa7986adf",
  )


  # com.twitter:chill-java:jar:0.9.2
  # com.twitter:chill_2.12:jar:0.9.2 got requested version
  native.maven_jar(
      name = "com_esotericsoftware_kryo_shaded",
      artifact = "com.esotericsoftware:kryo-shaded:4.0.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "4ae3bacfaea6459d8d63c6cf17c3718422fb2def",
  )


  native.maven_jar(
      name = "io_suzaku_boopickle",
      artifact = "io.suzaku:boopickle_2.12:1.3.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "a1cd66dfb24325d11cb38c340b7663e1ee4ed70e",
  )


  # org.apache.httpcomponents:httpclient:jar:4.4.1
  native.maven_jar(
      name = "commons_codec_commons_codec",
      artifact = "commons-codec:commons-codec:1.9",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "9ce04e34240f674bc72680f8b843b1457383161a",
  )


  # io.circe:circe-generic_2.12:jar:0.9.3
  native.maven_jar(
      name = "com_chuusai_shapeless",
      artifact = "com.chuusai:shapeless_2.12:2.3.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "6041e2c4871650c556a9c6842e43c04ed462b11f",
  )


  # com.fasterxml.jackson.module:jackson-module-paranamer:bundle:2.9.4
  native.maven_jar(
      name = "com_thoughtworks_paranamer_paranamer",
      artifact = "com.thoughtworks.paranamer:paranamer:2.8",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "619eba74c19ccf1da8ebec97a2d7f8ba05773dd6",
  )


  # io.circe:circe-parser_2.12:jar:0.9.3 got requested version
  # io.circe:circe-jawn_2.12:jar:0.9.3 got requested version
  # io.circe:circe-generic_2.12:jar:0.9.3 got requested version
  native.maven_jar(
      name = "io_circe_circe_core",
      artifact = "io.circe:circe-core_2.12:0.9.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "f4f8674788f571d840ed98fabf3237f72c86d1f0",
  )


  native.maven_jar(
      name = "org_apache_thrift_libthrift",
      artifact = "org.apache.thrift:libthrift:0.10.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "3201c5a6d85d3f030bae5a520abaaf81ef7df037",
  )


  # org.typelevel:cats-core_2.12:jar:1.0.1
  native.maven_jar(
      name = "org_typelevel_cats_macros",
      artifact = "org.typelevel:cats-macros_2.12:1.0.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "89374609c1ffe142c7fec887883aff779befb101",
  )


  # org.typelevel:cats-core_2.12:jar:1.0.1
  native.maven_jar(
      name = "org_typelevel_cats_kernel",
      artifact = "org.typelevel:cats-kernel_2.12:1.0.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "977ec6bbc1677502d0f6c26beeb0e5ee6c0da0ad",
  )


  # io.circe:circe-core_2.12:jar:0.9.3
  native.maven_jar(
      name = "io_circe_circe_numbers",
      artifact = "io.circe:circe-numbers_2.12:0.9.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "e8b931a2a2438d9ba84ff5ecbfb2a4ac7249b0d8",
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


  # io.circe:circe-core_2.12:jar:0.9.3
  native.maven_jar(
      name = "org_typelevel_cats_core",
      artifact = "org.typelevel:cats-core_2.12:1.0.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "5872b9db29c3e1245f841ac809d5d64b9e56eaa1",
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
      name = "com_fasterxml_jackson_module_jackson_module_scala",
      artifact = "com.fasterxml.jackson.module:jackson-module-scala_2.12:2.9.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "67f80f45d795bca57a74fc128d0839023e30a865",
  )


  # com.esotericsoftware:kryo-shaded:bundle:4.0.0
  native.maven_jar(
      name = "com_esotericsoftware_minlog",
      artifact = "com.esotericsoftware:minlog:1.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "8d2b87348c82b82e69ac2039ddbbc9d36dc69c9a",
  )


  native.maven_jar(
      name = "com_twitter_chill",
      artifact = "com.twitter:chill_2.12:0.9.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "d48a263c821593e5ae21fefda486dbabcd91d5af",
  )


  # io.circe:circe-jawn_2.12:jar:0.9.3
  native.maven_jar(
      name = "org_spire_math_jawn_parser",
      artifact = "org.spire-math:jawn-parser_2.12:0.11.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "e49f4a6294af0821d5348ad9f89a5ce8455fc1b3",
  )


  # com.twitter:chill_2.12:jar:0.9.2
  native.maven_jar(
      name = "com_twitter_chill_java",
      artifact = "com.twitter:chill-java:0.9.2",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "2eaacafa060bd70a49066dd806f9958a844c6be4",
  )


  # com.fasterxml.jackson.module:jackson-module-scala_2.12:bundle:2.9.4
  native.maven_jar(
      name = "com_fasterxml_jackson_module_jackson_module_paranamer",
      artifact = "com.fasterxml.jackson.module:jackson-module-paranamer:2.9.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "899b339804d5f9f797e8cca8cf235e0e769fa630",
  )


  # com.fasterxml.jackson.core:jackson-databind:bundle:2.9.4
  # com.fasterxml.jackson.module:jackson-module-scala_2.12:bundle:2.9.4 wanted version 2.9.4
  native.maven_jar(
      name = "com_fasterxml_jackson_core_jackson_annotations",
      artifact = "com.fasterxml.jackson.core:jackson-annotations:2.9.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "07c10d545325e3a6e72e06381afe469fd40eb701",
  )


  # io.circe:circe-parser_2.12:jar:0.9.3
  native.maven_jar(
      name = "io_circe_circe_jawn",
      artifact = "io.circe:circe-jawn_2.12:0.9.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "8462d202404f578f09cc9b89d7dca57dd94b09e5",
  )


  # org.apache.thrift:libthrift:pom:0.10.0
  native.maven_jar(
      name = "org_apache_httpcomponents_httpclient",
      artifact = "org.apache.httpcomponents:httpclient:4.4.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "016d0bc512222f1253ee6b64d389c84e22f697f0",
  )


  native.maven_jar(
      name = "io_circe_circe_generic",
      artifact = "io.circe:circe-generic_2.12:0.9.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "3cefb4701db62c625dd09ff580d8ea285f8d9c35",
  )


  # com.fasterxml.jackson.module:jackson-module-scala_2.12:bundle:2.9.4 got requested version
  # com.fasterxml.jackson.module:jackson-module-paranamer:bundle:2.9.4 got requested version
  native.maven_jar(
      name = "com_fasterxml_jackson_core_jackson_databind",
      artifact = "com.fasterxml.jackson.core:jackson-databind:2.9.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "498bbc3b94f566982c7f7c6d4d303fce365529be",
  )


  # com.chuusai:shapeless_2.12:bundle:2.3.3 got requested version
  # com.fasterxml.jackson.module:jackson-module-scala_2.12:bundle:2.9.4
  # org.typelevel:machinist_2.12:jar:0.6.2 wanted version 2.12.0
  # com.twitter:chill_2.12:jar:0.9.2 wanted version 2.12.1
  # org.typelevel:macro-compat_2.12:jar:1.1.1 wanted version 2.12.0
  # io.circe:circe-numbers_2.12:jar:0.9.3 wanted version 2.12.5
  # org.typelevel:cats-macros_2.12:jar:1.0.1 got requested version
  # org.spire-math:jawn-parser_2.12:jar:0.11.1 wanted version 2.12.2
  # org.typelevel:cats-core_2.12:jar:1.0.1 got requested version
  # io.circe:circe-core_2.12:jar:0.9.3 wanted version 2.12.5
  # org.typelevel:cats-kernel_2.12:jar:1.0.1 got requested version
  # io.circe:circe-generic_2.12:jar:0.9.3 wanted version 2.12.5
  # io.suzaku:boopickle_2.12:jar:1.3.0 got requested version
  # io.circe:circe-parser_2.12:jar:0.9.3 wanted version 2.12.5
  # org.scala-lang:scala-reflect:jar:2.12.4 got requested version
  # io.circe:circe-jawn_2.12:jar:0.9.3 wanted version 2.12.5
  native.maven_jar(
      name = "org_scala_lang_scala_library",
      artifact = "org.scala-lang:scala-library:2.12.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "7663f74ef944453c86cc7e6597ed33e9281f6412",
  )


  # com.fasterxml.jackson.module:jackson-module-scala_2.12:bundle:2.9.4 got requested version
  native.maven_jar(
      name = "com_fasterxml_jackson_core_jackson_core",
      artifact = "com.fasterxml.jackson.core:jackson-core:2.9.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "a9a71ec1aa37da47db168fede9a4a5fb5e374320",
  )


  native.maven_jar(
      name = "io_circe_circe_parser",
      artifact = "io.circe:circe-parser_2.12:0.9.3",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "9af9ad5e8a2027a7d93a2b21578f727f73f55d79",
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
      name = "org_typelevel_machinist",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_machinist//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
      ],
  )


  native.java_library(
      name = "org_typelevel_macro_compat",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_macro_compat//jar"],
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
      name = "io_suzaku_boopickle",
      visibility = ["//visibility:public"],
      exports = ["@io_suzaku_boopickle//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "commons_codec_commons_codec",
      visibility = ["//visibility:public"],
      exports = ["@commons_codec_commons_codec//jar"],
  )


  native.java_library(
      name = "com_chuusai_shapeless",
      visibility = ["//visibility:public"],
      exports = ["@com_chuusai_shapeless//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_typelevel_macro_compat",
      ],
  )


  native.java_library(
      name = "com_thoughtworks_paranamer_paranamer",
      visibility = ["//visibility:public"],
      exports = ["@com_thoughtworks_paranamer_paranamer//jar"],
  )


  native.java_library(
      name = "io_circe_circe_core",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_core//jar"],
      runtime_deps = [
          ":io_circe_circe_numbers",
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_typelevel_cats_core",
          ":org_typelevel_cats_kernel",
          ":org_typelevel_cats_macros",
          ":org_typelevel_machinist",
      ],
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
      name = "org_typelevel_cats_macros",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_cats_macros//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_typelevel_machinist",
      ],
  )


  native.java_library(
      name = "org_typelevel_cats_kernel",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_cats_kernel//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "io_circe_circe_numbers",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_numbers//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
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
      name = "org_typelevel_cats_core",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_cats_core//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
          ":org_typelevel_cats_kernel",
          ":org_typelevel_cats_macros",
          ":org_typelevel_machinist",
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
      name = "com_fasterxml_jackson_module_jackson_module_scala",
      visibility = ["//visibility:public"],
      exports = ["@com_fasterxml_jackson_module_jackson_module_scala//jar"],
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
      name = "com_esotericsoftware_minlog",
      visibility = ["//visibility:public"],
      exports = ["@com_esotericsoftware_minlog//jar"],
  )


  native.java_library(
      name = "com_twitter_chill",
      visibility = ["//visibility:public"],
      exports = ["@com_twitter_chill//jar"],
      runtime_deps = [
          ":com_esotericsoftware_kryo_shaded",
          ":com_esotericsoftware_minlog",
          ":com_twitter_chill_java",
          ":org_objenesis_objenesis",
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "org_spire_math_jawn_parser",
      visibility = ["//visibility:public"],
      exports = ["@org_spire_math_jawn_parser//jar"],
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
      name = "io_circe_circe_jawn",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_jawn//jar"],
      runtime_deps = [
          ":io_circe_circe_core",
          ":org_scala_lang_scala_library",
          ":org_spire_math_jawn_parser",
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
      name = "io_circe_circe_generic",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_generic//jar"],
      runtime_deps = [
          ":com_chuusai_shapeless",
          ":io_circe_circe_core",
          ":org_scala_lang_scala_library",
          ":org_typelevel_macro_compat",
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
      name = "io_circe_circe_parser",
      visibility = ["//visibility:public"],
      exports = ["@io_circe_circe_parser//jar"],
      runtime_deps = [
          ":io_circe_circe_core",
          ":io_circe_circe_jawn",
          ":org_scala_lang_scala_library",
          ":org_spire_math_jawn_parser",
      ],
  )


