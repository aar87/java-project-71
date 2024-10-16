package hexlet.code;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static String readFromFileLocation(String location) throws IOException {
        Path resultPath = Paths.get(System.getProperty("user.dir") + "/" + location).toAbsolutePath().normalize();
        byte[] fileBytes = Files.readAllBytes(resultPath);
        return new String(fileBytes, StandardCharsets.UTF_8);
    }
}
