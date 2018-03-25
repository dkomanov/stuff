#!/bin/sh

# run in bazel git repository

bazel run //generate_workspace -- \
  --repositories http://repo1.maven.org/maven2 \
  --artifact=org.drizzle.jdbc:drizzle-jdbc:1.4 \
  --artifact=org.mariadb.jdbc:mariadb-java-client:2.2.0 \
  --artifact=mysql:mysql-connector-java:6.0.6 \
  --artifact=com.wix:wix-embedded-mysql:3.0.0
