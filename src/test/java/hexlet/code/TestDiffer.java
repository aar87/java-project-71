package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class TestDiffer {

    @Test
    void testGenerate() throws IOException {
        Path resultPath = Path.of("src/test/resources/result.txt");

        byte[] fileBytes = Files.readAllBytes(resultPath.toRealPath());

        File jsonTestFile1 = Path.of("src/test/resources/file1.json").toFile();
        File jsonTestFile2 = Path.of("src/test/resources/file2.json").toFile();
        String correctResult = new String(fileBytes, StandardCharsets.UTF_8);

        String result = Differ.generate(jsonTestFile1, jsonTestFile2);

        assertEquals(result, correctResult);
    }

    @Test
    void testAddAllIfFirstIsEmpty() throws IOException {
        Path resultPath = Path.of("src/test/resources/allNewByFile1Json.txt");
        byte[] fileBytes = Files.readAllBytes(resultPath.toRealPath());
        String correctResult = new String(fileBytes, StandardCharsets.UTF_8);

        File firstFile = Path.of("src/test/resources/emptyFile.json").toFile();
        File secondFile = Path.of("src/test/resources/file1.json").toFile();

        assertEquals(Differ.generate(firstFile, secondFile), correctResult);
    }

    @Test
    void testRemoveAllIfSecondFileIsEmpty() throws IOException {
        Path resultPath = Path.of("src/test/resources/allMinusByFile1Json.txt");
        byte[] fileBytes = Files.readAllBytes(resultPath.toRealPath());
        String correctResult = new String(fileBytes, StandardCharsets.UTF_8);

        File firstFile = Path.of("src/test/resources/file1.json").toFile();
        File secondFile = Path.of("src/test/resources/emptyFile.json").toFile();

        assertEquals(Differ.generate(firstFile, secondFile), correctResult);
    }

    @Test
    void testDifferWithTwoEmptyFiles() throws IOException {
        File sameFile = Path.of("src/test/resources/emptyFile.json").toFile();
        String emptyResult = "{\n}";

        assertEquals(Differ.generate(sameFile, sameFile), emptyResult);
    }

    @Test
    void testNestedJsonDiffer() throws IOException {
        Path resultPath = Path.of("src/test/resources/expected/nested.txt");
        byte[] fileBytes = Files.readAllBytes(resultPath.toRealPath());
        String correctResult = new String(fileBytes, StandardCharsets.UTF_8);

        File firstFile = Path.of("src/test/resources/json/nested1.json").toFile();
        File secondFile = Path.of("src/test/resources/json/nested2.json").toFile();

        assertEquals(Differ.generate(firstFile, secondFile), correctResult);
    }

    @Test
    void testNestedYamlDiffer() throws IOException {
        Path resultPath = Path.of("src/test/resources/expected/nested.txt");
        byte[] fileBytes = Files.readAllBytes(resultPath.toRealPath());
        String correctResult = new String(fileBytes, StandardCharsets.UTF_8);

        File firstFile = Path.of("src/test/resources/yaml/nested1.yaml").toFile();
        File secondFile = Path.of("src/test/resources/yaml/nested2.yaml").toFile();

        assertEquals(Differ.generate(firstFile, secondFile), correctResult);
    }
}
