## Simple Version String Parsing

A demonstration how to optimize parsing of simple version string (eg. `1.0.0`), step by step.

### Running benchmarks

JDK8 doesn't have method `Integer.parseUnsignedInt`, so we don't need to run it (also, JDK8 is very old!).

```sh
DISABLE_JDK8=1 JDK_JMH_EXTRA="-prof gc" scripts/run-jmh.sh //src/com/komanov/ver/jmh:jmh version-parsing
```
