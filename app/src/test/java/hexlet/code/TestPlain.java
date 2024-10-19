package hexlet.code;

import formatters.Plain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestPlain {
    private static final Plain PLAIN_FORMATTER = new Plain();
    private static final int REPEAT_COUNT = 5;

    @Test
    public void testAddStart() {
        assertEquals(System.lineSeparator(), PLAIN_FORMATTER.addStart());
    }

    @Test
    public void testAddEnd() {
        assertEquals("", PLAIN_FORMATTER.addEnd());
    }

    @Test
    public void testAdd() {
        String resultAdd = PLAIN_FORMATTER.add(Utils.KEY_STRING, Utils.VALUE_STRING);
        assertTrue(isValidPlain(resultAdd));
        assertTrue(resultAdd.contains("added with value"));
    }

    @Test
    public void testRemove() {
        String removed = PLAIN_FORMATTER.remove(Utils.KEY_STRING, Utils.VALUE_STRING);
        assertTrue(removed.contains(Utils.KEY_STRING));
        assertTrue(removed.contains("removed"));
    }

    @Test
    public void testUpdate() {
        String updated = PLAIN_FORMATTER.update(Utils.KEY_STRING, Utils.VALUE_STRING, Utils.SECOND_VALUE_STRING);
        assertTrue(isValidPlain(updated));
        assertTrue(updated.contains(Utils.SECOND_VALUE_STRING));
        assertTrue(updated.contains("updated"));
    }

    @Test
    public void testNoChange() {
        String unchanged = PLAIN_FORMATTER.noChange(Utils.KEY_STRING, Utils.VALUE_STRING);
        assertEquals("", unchanged);
    }

    @Test
    public void testFinalize() {
        String result = Utils.KEY_STRING + ":" + Utils.VALUE_STRING + System.lineSeparator();
        String finalString = PLAIN_FORMATTER.finalize(result.repeat(REPEAT_COUNT));
        assertEquals(result.repeat(REPEAT_COUNT).trim(), finalString);
    }

    private boolean isValidPlain(String value) {
        boolean hasNewLine = String.valueOf(value.charAt(value.length() - 1)).equals(System.lineSeparator());
        return value.contains(Utils.KEY_STRING)
                && value.contains(Utils.VALUE_STRING)
                && value.contains("was")
                && hasNewLine;
    }
}
