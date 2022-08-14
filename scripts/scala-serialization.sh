#!/bin/bash -x

set -e
set -o pipefail

thrift -r --gen java -out src src/com/komanov/serialization/proto/events.thrift
thrift -r --gen java -out src src/com/komanov/serialization/proto/site.thrift
