#!/bin/sh -ex

TS=`date --iso-8601`
TARGET_DIR=src/com/komanov/readlines
TARGET_NAME=jmh
DATA_DIR=./site/public/data/read-utf8

JSON_PATH="${DATA_DIR}/jmh_${TS}.json"
LOG_PATH="${DATA_DIR}/jmh_${TS}.log"

bazel run //${TARGET_DIR}/${TARGET_NAME} -- -rf json -rff jmh.json ReadUtf8Benchmark | tee jmh.log
mv ./bazel-bin/${TARGET_DIR}/${TARGET_NAME}/${TARGET_NAME}.runfiles/stuff/jmh.json ${JSON_PATH}
mv ./jmh.log ${LOG_PATH}

git add ${JSON_PATH} ${LOG_PATH}

echo ${TS}
echo "Don't forget to change JS, commit, push and deploy!"
