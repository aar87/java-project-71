package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import hexlet.code.formatters.Formatter;

public final class Differ {
    public static String generate(String firstFile, String secondFile, String format) throws IOException {
        Map<String, Object> firstMap = Parser.process(Paths.get(firstFile).toAbsolutePath().normalize().toFile());
        Map<String, Object> secondMap = Parser.process(Paths.get(secondFile).toAbsolutePath().normalize().toFile());

        var f1 = new ArrayList<>(firstMap.keySet());
        var f2 = new ArrayList<>(secondMap.keySet());

        String[] total = Stream.concat(f1.stream(), f2.stream()).distinct().sorted().toArray(String[]::new);
        List<Map<String, Object>> diffsList = DiffStack.create(total, firstMap, secondMap);
        return Formatter.build(format).process(diffsList);
    }

    public static String generate(String firstFile, String secondFile) throws IOException {
        return generate(firstFile, secondFile, Formatter.STYLISH_FORMATTER);
    }
}
