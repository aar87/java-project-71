package hexlet.code;

import formatters.Stylish;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class TestStylish {
    private static final Stylish STYLISH_FORMATTER = new Stylish();
    private static final int REPEAT_COUNT = 5;

    @Test
    public void testAddStart() {
        assertEquals("{" + System.lineSeparator(), STYLISH_FORMATTER.addStart());
    }

    @Test
    public void testAddEnd() {
        assertEquals("}", STYLISH_FORMATTER.addEnd());
    }

    @Test
    public void testAdd() {
        String resultAdd = STYLISH_FORMATTER.add(Utils.KEY_STRING, Utils.VALUE_STRING);
        assertTrue(isValidStylish(resultAdd));
        assertTrue(resultAdd.contains("+"));
    }

    @Test
    public void testRemove() {
        String removed = STYLISH_FORMATTER.remove(Utils.KEY_STRING, Utils.VALUE_STRING);
        assertTrue(isValidStylish(removed));
        assertTrue(removed.contains("-"));
    }

    @Test
    public void testUpdate() {
        String removed = STYLISH_FORMATTER.update(Utils.KEY_STRING, Utils.VALUE_STRING, Utils.SECOND_VALUE_STRING);
        assertTrue(isValidStylish(removed));
        assertTrue(removed.contains("-"));
        assertTrue(removed.contains("+"));
    }

    @Test
    public void testNoChange() {
        String unchanged = STYLISH_FORMATTER.noChange(Utils.KEY_STRING, Utils.VALUE_STRING);
        assertTrue(isValidStylish(unchanged));
    }

    @Test
    public void testFinalize() {
        String result = Utils.KEY_STRING + ":" + Utils.VALUE_STRING + System.lineSeparator();
        String finalString = STYLISH_FORMATTER.finalize(result.repeat(REPEAT_COUNT));
        assertEquals(result.repeat(REPEAT_COUNT).trim(), finalString);
    }

    private boolean isValidStylish(String value) {
        boolean hasNewLine = String.valueOf(value.charAt(value.length() - 1)).equals(System.lineSeparator());
        return value.contains(Utils.KEY_STRING)
                && value.contains(Utils.VALUE_STRING)
                && value.contains(":") && hasNewLine;
    }
}
