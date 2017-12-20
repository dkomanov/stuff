package com.komanov.readlines;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ReadLinesJavaStreams {

    public static void forEachJava(Path path, Consumer<String> f) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(f);
        }
    }

}
