#!/bin/sh

# run in bazel git repository

SCALA_VERSION=_2.11
SLF4J_VERSION=1.7.25
JACKSON_VERSION=2.9.4
CIRCE_VERSION=0.9.2

bazel run //generate_workspace -- \
  --repositories http://repo1.maven.org/maven2 \
  --artifact=org.slf4j:slf4j-api:$SLF4J_VERSION \
  --artifact=org.slf4j:slf4j-nop:$SLF4J_VERSION \
  --artifact=com.fasterxml.jackson.core:jackson-databind:$JACKSON_VERSION \
  --artifact=com.fasterxml.jackson.core:jackson-core:$JACKSON_VERSION \
  --artifact=com.fasterxml.jackson.module:jackson-module-scala$SCALA_VERSION:$JACKSON_VERSION \
  --artifact=org.scala-lang.modules:scala-pickling$SCALA_VERSION:0.11.0-M2 \
  --artifact=me.chrons:boopickle$SCALA_VERSION:1.2.5 \
  --artifact=com.twitter:chill$SCALA_VERSION:0.9.2 \
  --artifact=org.apache.thrift:libthrift:0.10.0 \
  --artifact=io.circe:circe-core$SCALA_VERSION:$CIRCE_VERSION \
  --artifact=io.circe:circe-generic$SCALA_VERSION:$CIRCE_VERSION \
  --artifact=io.circe:circe-parser$SCALA_VERSION:$CIRCE_VERSION
