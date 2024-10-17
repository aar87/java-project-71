package hexlet.code;

import formatters.Json;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJson {
    private static final Json JSON_FORMATTER = new Json();

    @Test
    public void testAddStart() {
        assertEquals("{", JSON_FORMATTER.addStart());
    }

    @Test
    public void testAddEnd() {
        assertEquals("}", JSON_FORMATTER.addEnd());
    }

    @Test
    public void testAdd() {
        String resultAdd = JSON_FORMATTER.add(Utils.KEY_STRING, Utils.VALUE_STRING);
        assertTrue(isValidJson(resultAdd));
        assertTrue(resultAdd.contains("+"));
    }

    @Test
    public void testRemove() {
        String removed = JSON_FORMATTER.remove(Utils.KEY_STRING, Utils.VALUE_STRING);
        assertTrue(isValidJson(removed));
        assertTrue(removed.contains("-"));
    }

    @Test
    public void testUpdate() {
        String updated = JSON_FORMATTER.update(Utils.KEY_STRING, Utils.VALUE_STRING, Utils.SECOND_VALUE_STRING);
        assertEquals("", updated);
    }

    @Test
    public void testNoChange() {
        String unchanged = JSON_FORMATTER.noChange(Utils.KEY_STRING, Utils.VALUE_STRING);
        assertEquals("", unchanged);
    }

    @Test
    public void testFinalize() throws IOException {
        String jsonString = "{\"some\":\"value\",}";
        String finalString = JSON_FORMATTER.finalize(jsonString);
        assertEquals('}', finalString.charAt(finalString.length() - 1));
        assertNotEquals(',', finalString.charAt(finalString.length() - 2));
    }

    public boolean isValidJson(String jsonString) {
        boolean hasEndSemicolon = jsonString.charAt(jsonString.length() - 1) == ',';
        return jsonString.contains(Utils.KEY_STRING)
                && jsonString.contains(Utils.VALUE_STRING)
                && jsonString.contains(":") && hasEndSemicolon;
    }
}
