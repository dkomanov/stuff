#!/bin/sh -e

TS=`date --iso-8601`
TARGET_DIR=src/com/komanov/readlines
TARGET_NAME=jmh
DATA_DIR=./site/public/data/read-lines
DRIVE_TYPE="$1"

if [ ${DRIVE_TYPE} = "" ]
then
  echo "Expected drive type: ssd|hdd"
  exit 1
fi

bazel run //${TARGET_DIR}/${TARGET_NAME} -- -rf json -rff jmh.json | tee jmh.log
mv ./bazel-bin/${TARGET_DIR}/${TARGET_NAME}/${TARGET_NAME}.runfiles/stuff/jmh.json ${DATA_DIR}/jmh_${TS}_${DRIVE_TYPE}.json
mv ./jmh.log ${DATA_DIR}/jmh_${TS}_${DRIVE_TYPE}.log

echo ${TS}
echo "Don't forget to change JS, commit, push and deploy!"
