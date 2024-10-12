package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

public class Differ {

    public static Map<String, String> getData(File data) throws IOException {
        byte[] fileBytes = Files.readAllBytes(data.toPath());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fileBytes, new TypeReference<>() { });
    }

    public static String generate(File firstFile, File secondFile) throws IOException {
        Map<String, String> firstMap = getData(firstFile);
        Map<String, String> secondMap = getData(secondFile);

        var f1 = new ArrayList<>(getData(firstFile).keySet());
        var f2 = new ArrayList<>(secondMap.keySet());

        String[] total = Stream.concat(f1.stream(), f2.stream()).distinct().sorted().toArray(String[]::new);
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key: total) {
            String value = "  ";

            if (firstMap.containsKey(key)) {
                if (secondMap.containsKey(key)) {
                    if (firstMap.get(key).equals(secondMap.get(key))) {
                        value += "  " + key + ": " + firstMap.get(key);
                    } else {
                        value += "- " + key + ": " + firstMap.get(key) + System.lineSeparator();
                        value += "  + " + key + ": " + secondMap.get(key);
                    }
                } else {
                    value += "- " + key + ": " + firstMap.get(key);
                }
            } else {
                value += "+ " + key + ": " + secondMap.get(key);
            }

            result.append(value).append(System.lineSeparator());
        }

        result.append("}");

        return result.toString().trim();
    }
}
