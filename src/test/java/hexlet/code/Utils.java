package hexlet.code;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static final String KEY_STRING = "key";
    public static final String VALUE_STRING = "value";
    public static final String SECOND_VALUE_STRING = "value2";

    public static String readFromFileLocation(String location) throws IOException {
        Path resultPath = Paths.get(System.getProperty("user.dir") + "/" + location).toAbsolutePath().normalize();
        byte[] fileBytes = Files.readAllBytes(resultPath);
        return new String(fileBytes, StandardCharsets.UTF_8);
    }
}
