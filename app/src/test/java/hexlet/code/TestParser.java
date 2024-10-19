package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.IOException;

class TestParser {
    private static final String RESOURCE_FILE_DIR = "src/test/resources/differ/files/";

    @Test
    void testGetMapper() {
        String jsonFilename = "someName.json";
        String yamlFilename = "someName.yaml";

        assertInstanceOf(JsonMapper.class, Parser.getMapper(jsonFilename));
        assertInstanceOf(YAMLMapper.class, Parser.getMapper(yamlFilename));
    }

    @ParameterizedTest
    @CsvSource({"flat1,json", "flat1,yaml", "empty1,json", "empty1,yaml", "nested1,json", "nested1,yaml"})
    void testProcessFiles(String fileName, String format) throws IOException {
        assertTrue(testProcess(fileName, format));
    }

    boolean testProcess(String filename, String format) throws IOException {
        File fileInstance = new File(RESOURCE_FILE_DIR + filename + "." + format);
        return Parser.process(fileInstance) != null;
    }
}
