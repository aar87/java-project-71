package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatStyle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Map<String, Object>> diffs = new ArrayList<>();
        diffs.add(getMap("key1", DiffStack.State.UPDATED));
        diffs.add(getMap("key2", DiffStack.State.REMOVED));
        diffs.add(getMap("key3", DiffStack.State.ADDED));
        diffs.add(getMap("key4", DiffStack.State.UNCHANGED));

        FormatStyle formatter = Formatter.build(outputFormat);
        String result = formatter.process(diffs);

        String expected = Utils.readFromResourceLocation(outputFormat + "OutputResult.txt");
        assertEquals(expected, result);
    }

    Map<String, Object> getMap(String key, DiffStack.State state) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("state", state);
        map.put("fromIsComplex", false);
        map.put("toIsComplex", false);
        map.put("from", "fromValue");
        map.put("to", "toValue");
        return map;
    }
}
