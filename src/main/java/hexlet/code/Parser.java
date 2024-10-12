package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class Parser {
    public static final String YAML_FILE_TYPE = "yaml";
    public static final String YML_FILE_TYPE = "yml";
    public static final String JSON_FILE_TYPE = "json";

    public static ObjectMapper getMapper(String filename) {
        StringBuilder fileType = new StringBuilder();

        for (int i = filename.length() - 1; i >= 0; i--) {
            if (filename.charAt(i) == '.') {
                break;
            }

            fileType.append(filename.charAt(i));
        }

        String selectedFileType = fileType.reverse().toString();

        if (selectedFileType.equals(YAML_FILE_TYPE) || selectedFileType.equals(YML_FILE_TYPE)) {
            return new YAMLMapper();
        } else if (selectedFileType.equals(JSON_FILE_TYPE)) {
            return new JsonMapper();
        }

        throw new IllegalArgumentException("Unsupported file type: " + selectedFileType);
    }

    public static String toString(Object value) {
        if (value instanceof List<?>) {
            return List.of(value).toString();
        }

        return value.toString();
    }

    public static Map<String, String> process(File file) throws IOException {
        Path path = file.toPath();
        byte[] fileBytes = Files.readAllBytes(file.toPath());

        ObjectMapper objectMapper = getMapper(path.getFileName().toString());
        Map<String, Object> mapped = objectMapper.readValue(fileBytes, new TypeReference<>() { });

        Map<String, String> stringMapped = new HashMap<>();
        mapped.forEach((k, v) -> stringMapped.put(k, String.valueOf(v)));

        return stringMapped;
    }
}
