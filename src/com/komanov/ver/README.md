## Simple Version String Parsing

A demonstration how to optimize parsing of simple version string (eg. `1.0.0`), step by step.

### Running benchmarks

JDK8 doesn't have method `Integer.parseUnsignedInt`, so we don't need to run it (also, JDK8 is very old!).

```sh
DISABLE_JDK8=1 JDK_JMH_EXTRA="-prof gc" scripts/run-jmh.sh //src/com/komanov/ver/jmh:jmh version-parsing
```

### JOL

```sh
java -jar ~/dloads/jol-cli-0.17-full.jar internals scala.Some com.komanov.ver.Version -cp bazel-bin/src/com/komanov/str/jmh/jmh_deploy.jar:bazel-stuff/bazel-out/k8-fastbuild/bin/src/com/komanov/ver/_scalac/ver/classes
```
