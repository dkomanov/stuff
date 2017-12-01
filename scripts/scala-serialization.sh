#!/bin/sh -e

TS=`date --iso-8601`
TARGET_DIR=src/com/komanov/serialization
TARGET_NAME=jmh
DATA_DIR=./site/public/data/scala-serialization

bazel run -- //${TARGET_DIR}/${TARGET_NAME} -rf json -rff jmh.json | tee jmh.log
mv ./bazel-bin/${TARGET_DIR}/${TARGET_NAME}/${TARGET_NAME}.runfiles/stuff/jmh.json ${DATA_DIR}/jmh_${TS}.json
mv ./jmh.log ${DATA_DIR}/jmh_${TS}.log

echo ${TS}
echo "Don't forget to change JS, commit, push and deploy!"
