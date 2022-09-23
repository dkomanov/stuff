## How to JNI with Rust and Bazel
* 
* https://docs.rs/jni/latest/jni/
* [Bazel: Java app with JNI dependency](https://stackoverflow.com/questions/46256118/bazel-java-app-with-jni-dependency) and related [answer](https://github.com/hlopko/bazel-jni-example) is useful to understand how it works in bazel for C++. But requirement for `jvm_flags = ["-Djava.library.path=cpp"]` is redundant (you probably don't want to pass it everywhere).
