package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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

    public static boolean isComplex(Object value) {
        if (value == null) {
            return false;
        }

        return value instanceof Map || value instanceof List;
    }

    public static String valueToString(Object value) {
        if (value == null) {
            return "null";
        }

        return value.toString();
    }

    public static ArrayList<DiffDTO> createDiff(String[] keys, Map<String, Object> from, Map<String, Object> to) {
        ArrayList<DiffDTO> diffs = new ArrayList<>();
        for (String key: keys) {
            String fromValue = valueToString(from.get(key));
            String toValue = valueToString(to.get(key));
            DiffDTO diff = new DiffDTO(DiffType.UNCHANGED, key, fromValue, toValue, false);

            if (from.containsKey(key) && to.containsKey(key) && !safeCompare(from.get(key), to.get(key))) {
                diff.setState(DiffType.UPDATED);
                diff.setComplex(isComplex(to.get(key)));
            } else if (to.containsKey(key) && !from.containsKey(key)) {
                diff.setState(DiffType.ADDED);
                diff.setComplex(isComplex(from.get(key)));
            } else if (from.containsKey(key) && !to.containsKey(key)) {
                diff.setState(DiffType.REMOVED);
                diff.setComplex(isComplex(to.get(key)));
            }
            diffs.add(diff);
        }

        return diffs;
    }

    public static String generate(Path firstFile, Path secondFile, String format) throws IOException {
        Map<String, Object> firstMap = Parser.process(firstFile.toAbsolutePath().normalize().toFile());
        Map<String, Object> secondMap = Parser.process(secondFile.toAbsolutePath().normalize().toFile());

        var f1 = new ArrayList<>(firstMap.keySet());
        var f2 = new ArrayList<>(secondMap.keySet());

        String[] total = Stream.concat(f1.stream(), f2.stream()).distinct().sorted().toArray(String[]::new);
        ArrayList<DiffDTO> diffsList = createDiff(total, firstMap, secondMap);
        FormatStyle formatter = Formatter.build(format);
        return Formatter.format(diffsList, formatter);
    }

    public static String generate(Path firstFile, Path secondFile) throws IOException {
        return generate(firstFile, secondFile, Formatter.STYLISH_FORMATTER);
    }
}
