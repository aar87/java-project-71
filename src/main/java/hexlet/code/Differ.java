package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

import formatters.FormatStyle;
import formatters.Formatter;

public class Differ {
    public static Boolean safeCompare(Object first, Object second) {
        if (first == second) {
            return true;
        }

        if (first == null || second == null) {
            return false;
        }

        return first.equals(second);
    }

    public static String generate(File firstFile, File secondFile, String format) throws IOException {
        Map<String, Object> firstMap = Parser.process(firstFile);
        Map<String, Object> secondMap = Parser.process(secondFile);

        var f1 = new ArrayList<>(firstMap.keySet());
        var f2 = new ArrayList<>(secondMap.keySet());

        String[] total = Stream.concat(f1.stream(), f2.stream()).distinct().sorted().toArray(String[]::new);
        StringBuilder result = new StringBuilder();
        FormatStyle formatter = Formatter.build(format);

        result.append(formatter.addStart());

        for (String key: total) {
            String value;

            if (firstMap.containsKey(key)) {
                if (secondMap.containsKey(key)) {
                    if (safeCompare(firstMap.get(key), secondMap.get(key))) {
                        value = formatter.noChange(key, firstMap.get(key));
                    } else {
                        value = formatter.update(key, firstMap.get(key), secondMap.get(key));
                    }
                } else {
                    value = formatter.remove(key, firstMap.get(key));
                }
            } else {
                value = formatter.add(key, secondMap.get(key));
            }

            result.append(value);
        }

        result.append(formatter.addEnd());

        return formatter.finalize(result.toString());
    }

    public static String generate(File firstFile, File secondFile) throws IOException {
        return generate(firstFile, secondFile, Formatter.STYLISH_FORMATTER);
    }
}
