#!/bin/bash -x

set -e
set -o pipefail

TARGET=$1
NAME=$2

if [ "${TARGET}" = "" ] || [ "${NAME}" = "" ]
then
  echo "Usage: $0 //src/com/komanov/target/jmh:jmh my-benchmark"
  exit 1
fi

SCRIPT=$(realpath "$0")
SCRIPT_DIR=$(dirname "$SCRIPT")
ROOT_DIR=$(realpath "$SCRIPT_DIR"/..)
SITE_DIR=$(realpath "$ROOT_DIR"/../dkomanov.github.io)
DATA_DIR=$SITE_DIR/static/data/charts/$NAME

mkdir -p "$DATA_DIR"
cd "$ROOT_DIR"

LABEL="${TARGET}_deploy.jar"
bazel build "$LABEL"
JAR_FILE=$(bazel cquery --output starlark --starlark:expr '"\n".join([f.path for f in target.files.to_list()])' "$LABEL")

[  "${DISABLE_JDK8}" != "1" ] && /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java -jar "$JAR_FILE" -jvmArgsAppend "$JDK_ARGS_APPEND  $JDK8_ARGS_APPEND" $JDK_JMH_EXTRA  $JDK8_JMH_EXTRA -rf json -rff "$DATA_DIR"/jdk8.json  | stdbuf -oL sed "s#${SITE_DIR}##g" | tee "$DATA_DIR"/jdk8.log.txt
[ "${DISABLE_JDK11}" != "1" ] && /usr/lib/jvm/java-11-openjdk-amd64/bin/java    -jar "$JAR_FILE" -jvmArgsAppend "$JDK_ARGS_APPEND $JDK11_ARGS_APPEND" $JDK_JMH_EXTRA $JDK11_JMH_EXTRA -rf json -rff "$DATA_DIR"/jdk11.json | stdbuf -oL sed "s#${SITE_DIR}##g" | tee "$DATA_DIR"/jdk11.log.txt
[ "${DISABLE_JDK17}" != "1" ] && /usr/lib/jvm/java-17-openjdk-amd64/bin/java    -jar "$JAR_FILE" -jvmArgsAppend "$JDK_ARGS_APPEND $JDK17_ARGS_APPEND" $JDK_JMH_EXTRA $JDK17_JMH_EXTRA -rf json -rff "$DATA_DIR"/jdk17.json | stdbuf -oL sed "s#${SITE_DIR}##g" | tee "$DATA_DIR"/jdk17.log.txt
