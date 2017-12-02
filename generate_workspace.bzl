# The following dependencies were calculated from:
#
# generate_workspace --artifact=org.specs2:specs2-mock_2.11:3.8.8 --artifact=org.slf4j:slf4j-api:1.7.25 --artifact=org.slf4j:slf4j-nop:1.7.25 --artifact=org.drizzle.jdbc:drizzle-jdbc:1.4 --artifact=org.mariadb.jdbc:mariadb-java-client:2.2.0 --artifact=mysql:mysql-connector-java:6.0.6 --artifact=com.wix:wix-embedded-mysql:3.0.0 --artifact=commons-io:commons-io:2.6 --artifact=com.fasterxml.jackson.core:jackson-databind:2.9.2 --artifact=com.fasterxml.jackson.core:jackson-core:2.9.2 --artifact=com.fasterxml.jackson.module:jackson-module-scala_2.11:2.9.2 --artifact=org.scala-lang.modules:scala-pickling_2.11:0.11.0-M2 --artifact=me.chrons:boopickle_2.11:1.2.5 --artifact=com.twitter:chill_2.11:0.9.2 --artifact=org.apache.thrift:libthrift:0.10.0 --artifact=io.circe:circe-core_2.11:0.9.0-M2 --artifact=io.circe:circe-generic_2.11:0.9.0-M2 --artifact=io.circe:circe-parser_2.11:0.9.0-M2


def generated_maven_jars():
  # org.specs2:specs2-common_2.11:jar:3.8.8
  # org.scala-lang:scala-compiler:jar:2.11.7 wanted version 2.11.7
  # org.scala-lang.modules:scala-pickling_2.11:jar:0.11.0-M2 wanted version 2.11.7
  # org.typelevel:machinist_2.11:jar:0.6.2 got requested version
  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.2 got requested version
  native.maven_jar(
      name = "org_scala_lang_scala_reflect",
      artifact = "org.scala-lang:scala-reflect:2.11.8",
      sha1 = "b74530deeba742ab4f3134de0c2da0edc49ca361",
  )


  # org.typelevel:cats-macros_2.11:jar:1.0.0-RC1
  # org.typelevel:cats-core_2.11:jar:1.0.0-RC1 got requested version
  native.maven_jar(
      name = "org_typelevel_machinist_2_11",
      artifact = "org.typelevel:machinist_2.11:0.6.2",
      sha1 = "029c6a46d66b6616f8795a70753e6753975f42fc",
  )


  native.maven_jar(
      name = "commons_io_commons_io",
      artifact = "commons-io:commons-io:2.6",
  )


  # com.chuusai:shapeless_2.11:bundle:2.3.2 got requested version
  # io.circe:circe-generic_2.11:jar:0.9.0-M2
  native.maven_jar(
      name = "org_typelevel_macro_compat_2_11",
      artifact = "org.typelevel:macro-compat_2.11:1.1.1",
      sha1 = "0cb87cb74fd5fb118fede3f98075c2044616b35d",
  )


  # org.apache.httpcomponents:httpclient:jar:4.4.1
  native.maven_jar(
      name = "commons_codec_commons_codec",
      artifact = "commons-codec:commons-codec:1.9",
      sha1 = "9ce04e34240f674bc72680f8b843b1457383161a",
  )


  native.maven_jar(
      name = "org_drizzle_jdbc_drizzle_jdbc",
      artifact = "org.drizzle.jdbc:drizzle-jdbc:1.4",
  )


  # org.scala-lang.modules:scala-pickling_2.11:jar:0.11.0-M2
  native.maven_jar(
      name = "org_scala_lang_scala_compiler",
      artifact = "org.scala-lang:scala-compiler:2.11.7",
      sha1 = "1454c21d39a4d991006a2a47c164f675ea1dafaf",
  )


  # com.wix:wix-embedded-mysql:jar:3.0.0
  native.maven_jar(
      name = "de_flapdoodle_embed_de_flapdoodle_embed_process",
      artifact = "de.flapdoodle.embed:de.flapdoodle.embed.process:2.0.1",
      sha1 = "116a63e9f0f498b2375f4f6e7cd9e2fd8abe9a5f",
  )


  # com.fasterxml.jackson.module:jackson-module-paranamer:bundle:2.9.2
  native.maven_jar(
      name = "com_thoughtworks_paranamer_paranamer",
      artifact = "com.thoughtworks.paranamer:paranamer:2.8",
      sha1 = "619eba74c19ccf1da8ebec97a2d7f8ba05773dd6",
  )


  # de.flapdoodle.embed:de.flapdoodle.embed.process:bundle:2.0.1
  native.maven_jar(
      name = "net_java_dev_jna_jna_platform",
      artifact = "net.java.dev.jna:jna-platform:4.3.0",
      sha1 = "112d185ecfff7a3e6c33a114c566f9bf6f832244",
  )


  # org.typelevel:cats-core_2.11:jar:1.0.0-RC1
  native.maven_jar(
      name = "org_typelevel_cats_kernel_2_11",
      artifact = "org.typelevel:cats-kernel_2.11:1.0.0-RC1",
      sha1 = "c1a3af1df1af46709d81c3fadc2651b94a4727ab",
  )


  # org.mockito:mockito-core:jar:1.9.5
  # com.esotericsoftware:kryo-shaded:bundle:4.0.0 wanted version 2.2
  native.maven_jar(
      name = "org_objenesis_objenesis",
      artifact = "org.objenesis:objenesis:1.0",
      sha1 = "9b473564e792c2bdf1449da1f0b1b5bff9805704",
  )


  native.maven_jar(
      name = "com_fasterxml_jackson_module_jackson_module_scala_2_11",
      artifact = "com.fasterxml.jackson.module:jackson-module-scala_2.11:2.9.2",
  )


  # org.specs2:specs2-common_2.11:jar:3.8.8
  # org.scalaz:scalaz-effect_2.11:bundle:7.2.7 got requested version
  native.maven_jar(
      name = "org_scalaz_scalaz_core_2_11",
      artifact = "org.scalaz:scalaz-core_2.11:7.2.7",
      sha1 = "ebf85118d0bf4ce18acebf1d8475ee7deb7f19f1",
  )


  # net.java.dev.jna:jna-platform:jar:4.3.0 got requested version
  # de.flapdoodle.embed:de.flapdoodle.embed.process:bundle:2.0.1
  native.maven_jar(
      name = "net_java_dev_jna_jna",
      artifact = "net.java.dev.jna:jna:4.3.0",
      sha1 = "f11d386a05132f54a51c99085f016e496f345ea3",
  )


  native.maven_jar(
      name = "com_twitter_chill_2_11",
      artifact = "com.twitter:chill_2.11:0.9.2",
  )


  # com.esotericsoftware:kryo-shaded:bundle:4.0.0
  native.maven_jar(
      name = "com_esotericsoftware_minlog",
      artifact = "com.esotericsoftware:minlog:1.3",
      sha1 = "8d2b87348c82b82e69ac2039ddbbc9d36dc69c9a",
  )


  # org.mockito:mockito-core:jar:1.9.5 wanted version 1.1
  # org.specs2:specs2-mock_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_hamcrest_hamcrest_core",
      artifact = "org.hamcrest:hamcrest-core:1.3",
      sha1 = "42a25dc3219429f0e5d060061f71acb49bf010a0",
  )


  # io.circe:circe-jawn_2.11:jar:0.9.0-M2
  native.maven_jar(
      name = "org_spire_math_jawn_parser_2_11",
      artifact = "org.spire-math:jawn-parser_2.11:0.11.0",
      sha1 = "e17c156887c97440db2d8d3e513b0cfb7e5ae327",
  )


  # com.twitter:chill_2.11:jar:0.9.2
  native.maven_jar(
      name = "com_twitter_chill_java",
      artifact = "com.twitter:chill-java:0.9.2",
      sha1 = "2eaacafa060bd70a49066dd806f9958a844c6be4",
  )


  # io.circe:circe-parser_2.11:jar:0.9.0-M2
  native.maven_jar(
      name = "io_circe_circe_jawn_2_11",
      artifact = "io.circe:circe-jawn_2.11:0.9.0-M2",
      sha1 = "df7aca751b584481cd1d8c4a15ef75ad0c65cc33",
  )


  # org.specs2:specs2-common_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_scalaz_scalaz_effect_2_11",
      artifact = "org.scalaz:scalaz-effect_2.11:7.2.7",
      sha1 = "824bbb83da12224b3537c354c51eb3da72c435b5",
  )


  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.2
  native.maven_jar(
      name = "com_fasterxml_jackson_module_jackson_module_paranamer",
      artifact = "com.fasterxml.jackson.module:jackson-module-paranamer:2.9.2",
      sha1 = "3d8f5dcc16254665da6415f1bae79065c5b5d81a",
  )


  native.maven_jar(
      name = "io_circe_circe_generic_2_11",
      artifact = "io.circe:circe-generic_2.11:0.9.0-M2",
  )


  # com.fasterxml.jackson.module:jackson-module-paranamer:bundle:2.9.2 got requested version
  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.2 got requested version
  native.maven_jar(
      name = "com_fasterxml_jackson_core_jackson_databind",
      artifact = "com.fasterxml.jackson.core:jackson-databind:2.9.2",
  )


  # org.scalaz:scalaz-core_2.11:bundle:7.2.7 got requested version
  # me.chrons:boopickle_2.11:jar:1.2.5 got requested version
  # org.typelevel:cats-core_2.11:jar:1.0.0-RC1 wanted version 2.11.11
  # org.typelevel:cats-macros_2.11:jar:1.0.0-RC1 wanted version 2.11.11
  # io.circe:circe-core_2.11:jar:0.9.0-M2 wanted version 2.11.11
  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.2 got requested version
  # org.scalaz:scalaz-effect_2.11:bundle:7.2.7 got requested version
  # org.scala-lang.modules:scala-pickling_2.11:jar:0.11.0-M2 wanted version 2.11.7
  # org.scala-lang.modules:scala-parser-combinators_2.11:bundle:1.0.5 got requested version
  # org.specs2:specs2-core_2.11:jar:3.8.8 got requested version
  # org.specs2:specs2-matcher_2.11:jar:3.8.8 got requested version
  # io.circe:circe-jawn_2.11:jar:0.9.0-M2 wanted version 2.11.11
  # org.specs2:specs2-common_2.11:jar:3.8.8 got requested version
  # io.circe:circe-generic_2.11:jar:0.9.0-M2 wanted version 2.11.11
  # org.typelevel:machinist_2.11:jar:0.6.2 got requested version
  # org.spire-math:jawn-parser_2.11:jar:0.11.0 wanted version 2.11.11
  # org.typelevel:cats-kernel_2.11:jar:1.0.0-RC1 wanted version 2.11.11
  # org.scala-lang:scala-reflect:jar:2.11.8 got requested version
  # org.scala-lang.modules:scala-xml_2.11:bundle:1.0.5 wanted version 2.11.7
  # com.chuusai:shapeless_2.11:bundle:2.3.2 got requested version
  # org.specs2:specs2-mock_2.11:jar:3.8.8
  # org.scala-lang:scala-compiler:jar:2.11.7 wanted version 2.11.7
  # io.circe:circe-parser_2.11:jar:0.9.0-M2 wanted version 2.11.11
  # com.twitter:chill_2.11:jar:0.9.2 got requested version
  # io.circe:circe-numbers_2.11:jar:0.9.0-M2 wanted version 2.11.11
  # org.typelevel:macro-compat_2.11:jar:1.1.1 wanted version 2.11.7
  native.maven_jar(
      name = "org_scala_lang_scala_library",
      artifact = "org.scala-lang:scala-library:2.11.8",
      sha1 = "ddd5a8bced249bedd86fb4578a39b9fb71480573",
  )


  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.2 got requested version
  native.maven_jar(
      name = "com_fasterxml_jackson_core_jackson_core",
      artifact = "com.fasterxml.jackson.core:jackson-core:2.9.2",
  )


  # org.specs2:specs2-mock_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_specs2_specs2_core_2_11",
      artifact = "org.specs2:specs2-core_2.11:3.8.8",
      sha1 = "495bed00c73483f4f5f43945fde63c615d03e637",
  )


  # org.specs2:specs2-mock_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_mockito_mockito_core",
      artifact = "org.mockito:mockito-core:1.9.5",
      sha1 = "c3264abeea62c4d2f367e21484fbb40c7e256393",
  )


  # org.apache.httpcomponents:httpclient:jar:4.4.1
  # org.apache.thrift:libthrift:pom:0.10.0 got requested version
  native.maven_jar(
      name = "org_apache_httpcomponents_httpcore",
      artifact = "org.apache.httpcomponents:httpcore:4.4.1",
      sha1 = "f5aa318bda4c6c8d688c9d00b90681dcd82ce636",
  )


  # com.twitter:chill-java:jar:0.9.2
  # com.twitter:chill_2.11:jar:0.9.2 got requested version
  native.maven_jar(
      name = "com_esotericsoftware_kryo_shaded",
      artifact = "com.esotericsoftware:kryo-shaded:4.0.0",
      sha1 = "4ae3bacfaea6459d8d63c6cf17c3718422fb2def",
  )


  # io.circe:circe-jawn_2.11:jar:0.9.0-M2 got requested version
  # io.circe:circe-generic_2.11:jar:0.9.0-M2 got requested version
  # io.circe:circe-parser_2.11:jar:0.9.0-M2 got requested version
  native.maven_jar(
      name = "io_circe_circe_core_2_11",
      artifact = "io.circe:circe-core_2.11:0.9.0-M2",
  )


  native.maven_jar(
      name = "org_apache_thrift_libthrift",
      artifact = "org.apache.thrift:libthrift:0.10.0",
  )


  # org.typelevel:cats-core_2.11:jar:1.0.0-RC1
  native.maven_jar(
      name = "org_typelevel_cats_macros_2_11",
      artifact = "org.typelevel:cats-macros_2.11:1.0.0-RC1",
      sha1 = "1d22854bbfc23731e66923051120d2b58889c5a8",
  )


  # io.circe:circe-generic_2.11:jar:0.9.0-M2
  native.maven_jar(
      name = "com_chuusai_shapeless_2_11",
      artifact = "com.chuusai:shapeless_2.11:2.3.2",
      sha1 = "f40ed6e303d550293f5f8f3743681d98e31f2360",
  )


  # io.circe:circe-core_2.11:jar:0.9.0-M2
  native.maven_jar(
      name = "org_typelevel_cats_core_2_11",
      artifact = "org.typelevel:cats-core_2.11:1.0.0-RC1",
      sha1 = "8130fd279c01cf6cb241f5a65cdece2d726211ea",
  )


  native.maven_jar(
      name = "org_slf4j_slf4j_nop",
      artifact = "org.slf4j:slf4j-nop:1.7.25",
  )


  # io.circe:circe-core_2.11:jar:0.9.0-M2
  native.maven_jar(
      name = "io_circe_circe_numbers_2_11",
      artifact = "io.circe:circe-numbers_2.11:0.9.0-M2",
      sha1 = "51e104cf64ef590fe82eb2440b9ade6c4087e17f",
  )


  native.maven_jar(
      name = "me_chrons_boopickle_2_11",
      artifact = "me.chrons:boopickle_2.11:1.2.5",
  )


  # de.flapdoodle.embed:de.flapdoodle.embed.process:bundle:2.0.1 wanted version 1.7.10
  # org.slf4j:slf4j-nop:jar:1.7.25 got requested version
  # org.apache.thrift:libthrift:pom:0.10.0 wanted version 1.7.12
  native.maven_jar(
      name = "org_slf4j_slf4j_api",
      artifact = "org.slf4j:slf4j-api:1.7.25",
  )


  # org.apache.httpcomponents:httpclient:jar:4.4.1
  native.maven_jar(
      name = "commons_logging_commons_logging",
      artifact = "commons-logging:commons-logging:1.2",
      sha1 = "4bfc12adfe4842bf07b657f0369c4cb522955686",
  )


  # org.specs2:specs2-core_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_specs2_specs2_matcher_2_11",
      artifact = "org.specs2:specs2-matcher_2.11:3.8.8",
      sha1 = "d2e967737abef7421e47b8994a8c92784e624d62",
  )


  # com.wix:wix-embedded-mysql:jar:3.0.0
  native.maven_jar(
      name = "org_apache_commons_commons_compress",
      artifact = "org.apache.commons:commons-compress:1.14",
      sha1 = "7b18320d668ab080758bf5383d6d8fcf750babce",
  )


  # org.specs2:specs2-matcher_2.11:jar:3.8.8
  native.maven_jar(
      name = "org_specs2_specs2_common_2_11",
      artifact = "org.specs2:specs2-common_2.11:3.8.8",
      sha1 = "15bc009eaae3a574796c0f558d8696b57ae903c3",
  )


  # com.fasterxml.jackson.module:jackson-module-scala_2.11:bundle:2.9.2 wanted version 2.9.2
  # com.fasterxml.jackson.core:jackson-databind:bundle:2.9.2
  native.maven_jar(
      name = "com_fasterxml_jackson_core_jackson_annotations",
      artifact = "com.fasterxml.jackson.core:jackson-annotations:2.9.0",
      sha1 = "07c10d545325e3a6e72e06381afe469fd40eb701",
  )


  # org.scala-lang.modules:scala-pickling_2.11:jar:0.11.0-M2 wanted version 1.0.2
  # org.specs2:specs2-common_2.11:jar:3.8.8
  # org.scala-lang:scala-compiler:jar:2.11.7 got requested version
  native.maven_jar(
      name = "org_scala_lang_modules_scala_parser_combinators_2_11",
      artifact = "org.scala-lang.modules:scala-parser-combinators_2.11:1.0.5",
      sha1 = "cbd78079c99262f7a535d12a00a2dc3da6a266a0",
  )


  # org.apache.thrift:libthrift:pom:0.10.0
  native.maven_jar(
      name = "org_apache_httpcomponents_httpclient",
      artifact = "org.apache.httpcomponents:httpclient:4.4.1",
      sha1 = "016d0bc512222f1253ee6b64d389c84e22f697f0",
  )


  native.maven_jar(
      name = "mysql_mysql_connector_java",
      artifact = "mysql:mysql-connector-java:6.0.6",
  )


  # de.flapdoodle.embed:de.flapdoodle.embed.process:bundle:2.0.1
  native.maven_jar(
      name = "org_apache_commons_commons_lang3",
      artifact = "org.apache.commons:commons-lang3:3.1",
      sha1 = "905075e6c80f206bbe6cf1e809d2caa69f420c76",
  )


  # org.specs2:specs2-common_2.11:jar:3.8.8
  # org.scala-lang:scala-compiler:jar:2.11.7 got requested version
  native.maven_jar(
      name = "org_scala_lang_modules_scala_xml_2_11",
      artifact = "org.scala-lang.modules:scala-xml_2.11:1.0.5",
      sha1 = "77ac9be4033768cf03cc04fbd1fc5e5711de2459",
  )


  native.maven_jar(
      name = "org_specs2_specs2_mock_2_11",
      artifact = "org.specs2:specs2-mock_2.11:3.8.8",
  )


  native.maven_jar(
      name = "org_mariadb_jdbc_mariadb_java_client",
      artifact = "org.mariadb.jdbc:mariadb-java-client:2.2.0",
  )


  native.maven_jar(
      name = "com_wix_wix_embedded_mysql",
      artifact = "com.wix:wix-embedded-mysql:3.0.0",
  )


  native.maven_jar(
      name = "io_circe_circe_parser_2_11",
      artifact = "io.circe:circe-parser_2.11:0.9.0-M2",
  )


  native.maven_jar(
      name = "org_scala_lang_modules_scala_pickling_2_11",
      artifact = "org.scala-lang.modules:scala-pickling_2.11:0.11.0-M2",
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
      name = "org_typelevel_machinist_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_typelevel_machinist_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_scala_lang_scala_reflect",
      ],
  )


  native.java_library(
      name = "commons_io_commons_io",
      visibility = ["//visibility:public"],
      exports = ["@commons_io_commons_io//jar"],
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
      name = "commons_codec_commons_codec",
      visibility = ["//visibility:public"],
      exports = ["@commons_codec_commons_codec//jar"],
  )


  native.java_library(
      name = "org_drizzle_jdbc_drizzle_jdbc",
      visibility = ["//visibility:public"],
      exports = ["@org_drizzle_jdbc_drizzle_jdbc//jar"],
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
      name = "de_flapdoodle_embed_de_flapdoodle_embed_process",
      visibility = ["//visibility:public"],
      exports = ["@de_flapdoodle_embed_de_flapdoodle_embed_process//jar"],
      runtime_deps = [
          ":commons_io_commons_io",
          ":net_java_dev_jna_jna",
          ":net_java_dev_jna_jna_platform",
          ":org_apache_commons_commons_lang3",
          ":org_slf4j_slf4j_api",
      ],
  )


  native.java_library(
      name = "com_thoughtworks_paranamer_paranamer",
      visibility = ["//visibility:public"],
      exports = ["@com_thoughtworks_paranamer_paranamer//jar"],
  )


  native.java_library(
      name = "net_java_dev_jna_jna_platform",
      visibility = ["//visibility:public"],
      exports = ["@net_java_dev_jna_jna_platform//jar"],
      runtime_deps = [
          ":net_java_dev_jna_jna",
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
      name = "org_objenesis_objenesis",
      visibility = ["//visibility:public"],
      exports = ["@org_objenesis_objenesis//jar"],
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
      name = "org_scalaz_scalaz_core_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_scalaz_scalaz_core_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
      ],
  )


  native.java_library(
      name = "net_java_dev_jna_jna",
      visibility = ["//visibility:public"],
      exports = ["@net_java_dev_jna_jna//jar"],
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
      name = "org_hamcrest_hamcrest_core",
      visibility = ["//visibility:public"],
      exports = ["@org_hamcrest_hamcrest_core//jar"],
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
      name = "org_scalaz_scalaz_effect_2_11",
      visibility = ["//visibility:public"],
      exports = ["@org_scalaz_scalaz_effect_2_11//jar"],
      runtime_deps = [
          ":org_scala_lang_scala_library",
          ":org_scalaz_scalaz_core_2_11",
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
      name = "org_mockito_mockito_core",
      visibility = ["//visibility:public"],
      exports = ["@org_mockito_mockito_core//jar"],
      runtime_deps = [
          ":org_hamcrest_hamcrest_core",
          ":org_objenesis_objenesis",
      ],
  )


  native.java_library(
      name = "org_apache_httpcomponents_httpcore",
      visibility = ["//visibility:public"],
      exports = ["@org_apache_httpcomponents_httpcore//jar"],
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
      name = "org_apache_commons_commons_compress",
      visibility = ["//visibility:public"],
      exports = ["@org_apache_commons_commons_compress//jar"],
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
      name = "mysql_mysql_connector_java",
      visibility = ["//visibility:public"],
      exports = ["@mysql_mysql_connector_java//jar"],
  )


  native.java_library(
      name = "org_apache_commons_commons_lang3",
      visibility = ["//visibility:public"],
      exports = ["@org_apache_commons_commons_lang3//jar"],
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


  native.java_library(
      name = "org_mariadb_jdbc_mariadb_java_client",
      visibility = ["//visibility:public"],
      exports = ["@org_mariadb_jdbc_mariadb_java_client//jar"],
  )


  native.java_library(
      name = "com_wix_wix_embedded_mysql",
      visibility = ["//visibility:public"],
      exports = ["@com_wix_wix_embedded_mysql//jar"],
      runtime_deps = [
          ":commons_io_commons_io",
          ":de_flapdoodle_embed_de_flapdoodle_embed_process",
          ":net_java_dev_jna_jna",
          ":net_java_dev_jna_jna_platform",
          ":org_apache_commons_commons_compress",
          ":org_apache_commons_commons_lang3",
          ":org_slf4j_slf4j_api",
      ],
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


