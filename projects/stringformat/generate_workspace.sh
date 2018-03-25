#!/bin/sh

# run in bazel git repository

SCALA_VERSION=_2.11
SPECS2_VERSION=3.8.8
SLF4J_VERSION=1.7.25

bazel run //generate_workspace -- \
  --repositories http://repo1.maven.org/maven2 \
  --artifact=org.specs2:specs2-mock$SCALA_VERSION:$SPECS2_VERSION \
  --artifact=org.slf4j:slf4j-api:$SLF4J_VERSION
