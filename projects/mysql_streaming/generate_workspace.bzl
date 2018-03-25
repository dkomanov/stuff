# The following dependencies were calculated from:
#
# generate_workspace --repositories http://repo1.maven.org/maven2 --artifact=org.drizzle.jdbc:drizzle-jdbc:1.4 --artifact=org.mariadb.jdbc:mariadb-java-client:2.2.0 --artifact=mysql:mysql-connector-java:6.0.6 --artifact=com.wix:wix-embedded-mysql:3.0.0


def generated_maven_jars():
  # com.wix:wix-embedded-mysql:jar:3.0.0
  native.maven_jar(
      name = "de_flapdoodle_embed_de_flapdoodle_embed_process",
      artifact = "de.flapdoodle.embed:de.flapdoodle.embed.process:2.0.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "116a63e9f0f498b2375f4f6e7cd9e2fd8abe9a5f",
  )


  # de.flapdoodle.embed:de.flapdoodle.embed.process:bundle:2.0.1
  native.maven_jar(
      name = "org_slf4j_slf4j_api",
      artifact = "org.slf4j:slf4j-api:1.7.10",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "5b338f206827d88acd890739f400a9b7664e5984",
  )


  native.maven_jar(
      name = "mysql_mysql_connector_java",
      artifact = "mysql:mysql-connector-java:6.0.6",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "1d19b184dbc596008cc71c83596f051c3ec4097f",
  )


  # net.java.dev.jna:jna-platform:jar:4.3.0 got requested version
  # de.flapdoodle.embed:de.flapdoodle.embed.process:bundle:2.0.1
  native.maven_jar(
      name = "net_java_dev_jna_jna",
      artifact = "net.java.dev.jna:jna:4.3.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "f11d386a05132f54a51c99085f016e496f345ea3",
  )


  # de.flapdoodle.embed:de.flapdoodle.embed.process:bundle:2.0.1
  native.maven_jar(
      name = "org_apache_commons_commons_lang3",
      artifact = "org.apache.commons:commons-lang3:3.1",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "905075e6c80f206bbe6cf1e809d2caa69f420c76",
  )


  # de.flapdoodle.embed:de.flapdoodle.embed.process:bundle:2.0.1
  native.maven_jar(
      name = "net_java_dev_jna_jna_platform",
      artifact = "net.java.dev.jna:jna-platform:4.3.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "112d185ecfff7a3e6c33a114c566f9bf6f832244",
  )


  # com.wix:wix-embedded-mysql:jar:3.0.0
  native.maven_jar(
      name = "org_apache_commons_commons_compress",
      artifact = "org.apache.commons:commons-compress:1.14",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "7b18320d668ab080758bf5383d6d8fcf750babce",
  )


  # de.flapdoodle.embed:de.flapdoodle.embed.process:bundle:2.0.1
  native.maven_jar(
      name = "commons_io_commons_io",
      artifact = "commons-io:commons-io:2.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "b1b6ea3b7e4aa4f492509a4952029cd8e48019ad",
  )


  native.maven_jar(
      name = "org_drizzle_jdbc_drizzle_jdbc",
      artifact = "org.drizzle.jdbc:drizzle-jdbc:1.4",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "4fe883d19ba048b8c9966aca467ff40103f7da2f",
  )


  native.maven_jar(
      name = "org_mariadb_jdbc_mariadb_java_client",
      artifact = "org.mariadb.jdbc:mariadb-java-client:2.2.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "cbad0797cd528a899f39b1b5ef833e11b8fc3a20",
  )


  native.maven_jar(
      name = "com_wix_wix_embedded_mysql",
      artifact = "com.wix:wix-embedded-mysql:3.0.0",
      repository = "http://repo1.maven.org/maven2/",
      sha1 = "44ffe8af1505321b474966b8bf0fda1136914273",
  )




def generated_java_libraries():
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
      name = "org_slf4j_slf4j_api",
      visibility = ["//visibility:public"],
      exports = ["@org_slf4j_slf4j_api//jar"],
  )


  native.java_library(
      name = "mysql_mysql_connector_java",
      visibility = ["//visibility:public"],
      exports = ["@mysql_mysql_connector_java//jar"],
  )


  native.java_library(
      name = "net_java_dev_jna_jna",
      visibility = ["//visibility:public"],
      exports = ["@net_java_dev_jna_jna//jar"],
  )


  native.java_library(
      name = "org_apache_commons_commons_lang3",
      visibility = ["//visibility:public"],
      exports = ["@org_apache_commons_commons_lang3//jar"],
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
      name = "org_apache_commons_commons_compress",
      visibility = ["//visibility:public"],
      exports = ["@org_apache_commons_commons_compress//jar"],
  )


  native.java_library(
      name = "commons_io_commons_io",
      visibility = ["//visibility:public"],
      exports = ["@commons_io_commons_io//jar"],
  )


  native.java_library(
      name = "org_drizzle_jdbc_drizzle_jdbc",
      visibility = ["//visibility:public"],
      exports = ["@org_drizzle_jdbc_drizzle_jdbc//jar"],
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


