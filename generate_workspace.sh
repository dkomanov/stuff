#!/bin/sh

# run in bazel git repository

SCALA_VERSION=_2.11
SPECS2_VERSION=3.8.6
SLF4J_VERSION=1.7.21
JACKSON_VERSION=2.7.3
SCALAPB_VERSION=0.5.46
JMH_VERSION=1.16

bazel run //src/tools/generate_workspace -- \
  --artifact=org.specs2:specs2-junit$SCALA_VERSION:$SPECS2_VERSION \
  --artifact=org.specs2:specs2-matcher-extra$SCALA_VERSION:$SPECS2_VERSION \
  --artifact=org.specs2:specs2-mock$SCALA_VERSION:$SPECS2_VERSION \
  --artifact=org.openjdk.jmh:jmh-core:$JMH_VERSION \
  --artifact=org.slf4j:slf4j-api:$SLF4J_VERSION \
  --artifact=org.slf4j:slf4j-nop:$SLF4J_VERSION \
  --artifact=org.slf4j:slf4j-simple:$SLF4J_VERSION \
  --artifact=org.drizzle.jdbc:drizzle-jdbc:1.3 \
  --artifact=org.mariadb.jdbc:mariadb-java-client:1.4.6 \
  --artifact=mysql:mysql-connector-java:6.0.3 \
  --artifact=com.wix:wix-embedded-mysql:2.0.0 \
  --artifact=commons-io:commons-io:2.4 \
  --artifact=com.fasterxml.jackson.core:jackson-databind:$JACKSON_VERSION \
  --artifact=com.fasterxml.jackson.core:jackson-core:$JACKSON_VERSION \
  --artifact=com.fasterxml.jackson.module:jackson-module-scala$SCALA_VERSION:$JACKSON_VERSION \
  --artifact=com.trueaccord.scalapb:scalapb-runtime$SCALA_VERSION:$SCALAPB_VERSION \
  --artifact=org.scala-lang.modules:scala-pickling$SCALA_VERSION:0.11.0-M2 \
  --artifact=me.chrons:boopickle$SCALA_VERSION:1.2.4 \
  --artifact=com.twitter:chill$SCALA_VERSION:0.8.0 \
  --artifact=org.apache.thrift:libthrift:0.9.1 \
  --artifact=com.twitter:scrooge-core$SCALA_VERSION:4.7.0
