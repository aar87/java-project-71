package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;
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

    public static ArrayList<DiffDTO> createDiff(String[] keys, Map<String, Object> from, Map<String, Object> to) {
        ArrayList<DiffDTO> diffs = new ArrayList<>();
        for (String key: keys) {
            Object fromValue = from.get(key);
            Object toValue = to.get(key);
            DiffDTO diff = new DiffDTO(DiffType.UNCHANGED, key, fromValue, toValue);

            if (from.containsKey(key) && to.containsKey(key) && !safeCompare(from.get(key), to.get(key))) {
                diff.setState(DiffType.UPDATED);
            } else if (to.containsKey(key) && !from.containsKey(key)) {
                diff.setState(DiffType.ADDED);
            } else if (from.containsKey(key) && !to.containsKey(key)) {
                diff.setState(DiffType.REMOVED);
            }
            diffs.add(diff);
        }

        return diffs;
    }

    public static String generate(String firstFile, String secondFile, String format) throws IOException {
        Map<String, Object> firstMap = Parser.process(Paths.get(firstFile).toAbsolutePath().normalize().toFile());
        Map<String, Object> secondMap = Parser.process(Paths.get(secondFile).toAbsolutePath().normalize().toFile());

        var f1 = new ArrayList<>(firstMap.keySet());
        var f2 = new ArrayList<>(secondMap.keySet());

        String[] total = Stream.concat(f1.stream(), f2.stream()).distinct().sorted().toArray(String[]::new);
        ArrayList<DiffDTO> diffsList = createDiff(total, firstMap, secondMap);
        FormatStyle formatter = Formatter.build(format);
        return Formatter.format(diffsList, formatter);
    }

    public static String generate(String firstFile, String secondFile) throws IOException {
        return generate(firstFile, secondFile, Formatter.STYLISH_FORMATTER);
    }
}
