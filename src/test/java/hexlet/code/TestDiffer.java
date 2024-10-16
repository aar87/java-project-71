package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDiffer {
    private static final String RESOURCE_FILE_DIR = "src/test/resources/differ/files/";

    @Test
    void testSafeCompare() {
        assertEquals(true, Differ.safeCompare(null, null));
        assertEquals(false, Differ.safeCompare(0, null));
        assertEquals(false, Differ.safeCompare("", null));
    }

    @ParameterizedTest
    @CsvSource({"json,flat", "yaml,flat", "json,nested", "yaml,nested", "json,empty", "yaml,empty"})
    void testGenerateExpected(String format, String fileType) throws IOException {
        Path resultPath = Path.of(RESOURCE_FILE_DIR + fileType + "Result" + ".txt");

        byte[] fileBytes = Files.readAllBytes(resultPath.toRealPath());
        String expected = new String(fileBytes, StandardCharsets.UTF_8);

        assertEquals(generate(format, fileType), expected);
    }

    String generate(String format, String fileType) throws IOException {
        File jsonTestFile1 = Path.of(RESOURCE_FILE_DIR + fileType + "1." + format).toFile();
        File jsonTestFile2 = Path.of(RESOURCE_FILE_DIR + fileType + "2." + format).toFile();
        return Differ.generate(jsonTestFile1, jsonTestFile2);
    }

    @ParameterizedTest
    @CsvSource({"json,flat", "yaml,flat", "json,nested", "yaml,nested"})
    void testAddAllIfFirstIsEmpty(String format, String fileType) throws IOException {
        Path resultPath = Path.of(RESOURCE_FILE_DIR + fileType + "addResult" + ".txt");

        byte[] fileBytes = Files.readAllBytes(resultPath.toRealPath());
        String expected = new String(fileBytes, StandardCharsets.UTF_8);

        assertEquals(expected, generateWithFirstEmpty(format, fileType));
    }

    String generateWithFirstEmpty(String format, String fileType) throws IOException {
        File jsonTestFile1 = Path.of(RESOURCE_FILE_DIR + "empty1." + format).toFile();
        File jsonTestFile2 = Path.of(RESOURCE_FILE_DIR + fileType + "1." + format).toFile();
        return Differ.generate(jsonTestFile1, jsonTestFile2);
    }

    @ParameterizedTest
    @CsvSource({"json,flat", "yaml,flat", "json,nested", "yaml,nested"})
    void testRemoveAllIfSecondFileIsEmpty(String format, String fileType) throws IOException {
        Path resultPath = Path.of(RESOURCE_FILE_DIR + fileType + "removeResult" + ".txt");

        byte[] fileBytes = Files.readAllBytes(resultPath.toRealPath());
        String expected = new String(fileBytes, StandardCharsets.UTF_8);

        assertEquals(expected, generateWithSecondEmpty(format, fileType));
    }

    String generateWithSecondEmpty(String format, String fileType) throws IOException {
        File jsonTestFile1 = Path.of(RESOURCE_FILE_DIR + fileType + "1." + format).toFile();
        File jsonTestFile2 = Path.of(RESOURCE_FILE_DIR + "empty1." + format).toFile();
        return Differ.generate(jsonTestFile1, jsonTestFile2);
    }

    @ParameterizedTest
    @CsvSource({"json,flat", "yaml,flat", "json,nested", "yaml,nested"})
    void testWithEqualFiles(String format, String fileType) throws IOException {
        Path resultPath = Path.of(RESOURCE_FILE_DIR + fileType + "SameResult" + ".txt");

        byte[] fileBytes = Files.readAllBytes(resultPath.toRealPath());
        String expected = new String(fileBytes, StandardCharsets.UTF_8);

        assertEquals(expected, generateWithSameFiles(format, fileType));
    }

    String generateWithSameFiles(String format, String fileType) throws IOException {
        File jsonTestFile1 = Path.of(RESOURCE_FILE_DIR + fileType + "1." + format).toFile();
        return Differ.generate(jsonTestFile1, jsonTestFile1);
    }
}
