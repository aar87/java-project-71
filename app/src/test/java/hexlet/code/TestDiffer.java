package hexlet.code;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDiffer {
    @ParameterizedTest
    @CsvSource({"json,flat", "yaml,flat", "json,nested", "yaml,nested", "json,empty", "yaml,empty"})
    void testGenerateExpected(String format, String fileType) throws IOException {
        String expected = Utils.readFromResourceLocation(fileType + "Result" + ".txt");
        assertEquals(expected, generate(format, fileType));
    }

    String generate(String format, String fileType) throws IOException {
        String file1 = Utils.RESOURCE_FILE_DIR + fileType + "1." + format;
        String file2 = Utils.RESOURCE_FILE_DIR + fileType + "2." + format;
        return Differ.generate(file1, file2);
    }

    @ParameterizedTest
    @CsvSource({"json,flat", "yaml,flat", "json,nested", "yaml,nested"})
    void testAddAllIfFirstIsEmpty(String format, String fileType) throws IOException {
        String expected = Utils.readFromResourceLocation(fileType + "AddResult" + ".txt");
        assertEquals(expected, generateWithFirstEmpty(format, fileType));
    }

    String generateWithFirstEmpty(String format, String fileType) throws IOException {
        String file1 = Utils.RESOURCE_FILE_DIR + "empty1." + format;
        String file2 = Utils.RESOURCE_FILE_DIR + fileType + "1." + format;
        return Differ.generate(file1, file2);
    }

    @ParameterizedTest
    @CsvSource({"json,flat", "yaml,flat", "json,nested", "yaml,nested"})
    void testRemoveAllIfSecondFileIsEmpty(String format, String fileType) throws IOException {
        String expected = Utils.readFromResourceLocation(fileType + "RemoveResult" + ".txt");
        assertEquals(expected, generateWithSecondEmpty(format, fileType));
    }

    String generateWithSecondEmpty(String format, String fileType) throws IOException {
        String file1 = Utils.RESOURCE_FILE_DIR + fileType + "1." + format;
        String file2 = Utils.RESOURCE_FILE_DIR + "empty1." + format;
        return Differ.generate(file1, file2);
    }

    @ParameterizedTest
    @CsvSource({"json,flat", "yaml,flat", "json,nested", "yaml,nested"})
    void testWithEqualFiles(String format, String fileType) throws IOException {
        String expected = Utils.readFromResourceLocation(fileType + "SameResult" + ".txt");
        assertEquals(expected, generateWithSameFiles(format, fileType));
    }

    String generateWithSameFiles(String format, String fileType) throws IOException {
        String sameFilePath = Utils.RESOURCE_FILE_DIR + fileType + "1." + format;
        return Differ.generate(sameFilePath, sameFilePath);
    }
}
