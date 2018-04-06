#!/bin/sh -ex

DIR=`dirname $0`/..
DIR=`pwd $DIR`
TS=`date --iso-8601`
TARGET=src/com/komanov/serialization/jmh
TARGET_NAME=`basename $TARGET`
DATA_DIR=${DIR}/site/public/data/scala-serialization

if test ! -d /the/dir; then echo "no data dir, maybe you forgot to link site?"; exit 1; fi

cd ${DIR}/projects/scala_serialization
bazel run -- //${TARGET} -rf json -rff jmh.json | tee jmh.log
mv ./bazel-bin/${TARGET}/${TARGET_NAME}.runfiles/stuff/jmh.json ${DATA_DIR}/jmh_${TS}.json
mv ./jmh.log ${DATA_DIR}/jmh_${TS}.log
cd ${DIR}

echo ${TS}
echo "Don't forget to change JS, commit, push and deploy!"
