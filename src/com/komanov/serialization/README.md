# Scala serialization

A source code for the article "Scala Serialization" at [medium](https://medium.com/@dkomanov/scala-serialization-419d175c888a).

Recent charts for the article is at https://dkomanov.github.io/charts/scala-serialization/.

Run:
```
JDK17_ARGS_APPEND="--add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang.invoke=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED" scripts/run-jmh.sh //src/com/komanov/serialization/jmh:jmh scala-serialization-2022
```
