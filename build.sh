#!/bin/sh -ex

export BASE_DIR=`dirname $0`

build() {
  cd ${BASE_DIR}/$1

  echo 'building '$1 && echo -en 'travis_fold:start:'$1'\\r'

  TARGET="//..."
  BAZEL_OPTS="--output_base=$HOME/.cache/bazel"
  echo 'fetching dependencies...' && echo -en 'travis_fold:start:fetch\\r'
  bazel $BAZEL_OPTS fetch --curses=no $TARGET
  echo -en 'travis_fold:end:fetch\\r'
  echo 'building...' && echo -en 'travis_fold:start:build\\r'
  bazel $BAZEL_OPTS build --curses=no $TARGET
  echo -en 'travis_fold:end:build\\r'
  echo 'running tests...' && echo -en 'travis_fold:start:test\\r'
  bazel $BAZEL_OPTS test --curses=no $TARGET
  echo -en 'travis_fold:end:test\\r'

  echo 'complete '$1 && echo -en 'travis_fold:end:'$1'\\r'
}

build .
#build projects/mysql_streaming
build projects/scala_serialization
build projects/stringformat
