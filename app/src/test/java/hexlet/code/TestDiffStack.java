package hexlet.code;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiffStack {

    @Test
    void testSafeCompare() {
        assertEquals(true, DiffStack.safeCompare(null, null));
        assertEquals(false, DiffStack.safeCompare(0, null));
        assertEquals(false, DiffStack.safeCompare("", null));
    }
}
