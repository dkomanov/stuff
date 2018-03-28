#!/bin/sh -ex

# run in bazel git repository

DIR=`dirname $0`

SCALA_VERSION=_2.12
SCALA_LABEL=_2_12
SLF4J_VERSION=1.7.25
JACKSON_VERSION=2.9.4
CIRCE_VERSION=0.9.3

bazel run //generate_workspace -- \
  --repositories http://repo1.maven.org/maven2 \
  --artifact=org.slf4j:slf4j-api:$SLF4J_VERSION \
  --artifact=org.slf4j:slf4j-nop:$SLF4J_VERSION \
  --artifact=com.fasterxml.jackson.core:jackson-databind:$JACKSON_VERSION \
  --artifact=com.fasterxml.jackson.core:jackson-core:$JACKSON_VERSION \
  --artifact=com.fasterxml.jackson.module:jackson-module-scala$SCALA_VERSION:$JACKSON_VERSION \
  --artifact=io.suzaku:boopickle$SCALA_VERSION:1.3.0 \
  --artifact=com.twitter:chill$SCALA_VERSION:0.9.2 \
  --artifact=org.apache.thrift:libthrift:0.10.0 \
  --artifact=io.circe:circe-core$SCALA_VERSION:$CIRCE_VERSION \
  --artifact=io.circe:circe-generic$SCALA_VERSION:$CIRCE_VERSION \
  --artifact=io.circe:circe-parser$SCALA_VERSION:$CIRCE_VERSION \
  2>&1 | tee gw.log

OUTPUT_FILE=`cat gw.log | grep Wrote | sed 's/Wrote //g'`

cat ${OUTPUT_FILE} | \
  sed -r "s/(name.*)${SCALA_LABEL}/\1/" | \
  sed -r "s/(:.+)${SCALA_LABEL}/\1/" | \
  sed -r "s/(exports.+@.+)${SCALA_LABEL}/\1/" \
  > ${DIR}/third_party_deps.bzl

