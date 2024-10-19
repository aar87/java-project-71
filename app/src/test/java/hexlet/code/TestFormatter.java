package hexlet.code;

import formatters.Json;
import formatters.Plain;
import formatters.Stylish;
import formatters.Formatter;
import formatters.FormatStyle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class TestFormatter {

    @Test
    void testBuild() {
        assertInstanceOf(Stylish.class, Formatter.build("stylish"));
        assertInstanceOf(Stylish.class, Formatter.build("undefined"));
        assertInstanceOf(Stylish.class, Formatter.build(""));
        assertInstanceOf(Plain.class, Formatter.build("plain"));
        assertInstanceOf(Json.class, Formatter.build("json"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"stylish", "plain", "json"})
    void testOutputFormat(String outputFormat) throws IOException {
        ArrayList<DiffDTO> diffs = new ArrayList<>();
        diffs.add(new DiffDTO(DiffType.UPDATED, "key1", "fromValue", "toValue", false));
        diffs.add(new DiffDTO(DiffType.REMOVED, "key2", "fromValue", "toValue", false));
        diffs.add(new DiffDTO(DiffType.ADDED, "key3", "fromValue", "toValue", false));
        diffs.add(new DiffDTO(DiffType.UNCHANGED, "key4", "fromValue", "toValue", false));

        FormatStyle formatter = Formatter.build(outputFormat);
        String result = Formatter.format(diffs, formatter);

        String expected = Utils.readFromResourceLocation(outputFormat + "OutputResult.txt");
        assertEquals(expected, result);
    }
}
