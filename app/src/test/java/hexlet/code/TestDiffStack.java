package hexlet.code;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestDiffStack {

    @Test
    void testSafeCompare() {
        assertEquals(true, DiffStack.safeCompare(null, null));
        assertEquals(false, DiffStack.safeCompare(0, null));
        assertEquals(false, DiffStack.safeCompare("", null));
    }

    @Test
    void testIsComplex() {
        assertFalse(DiffStack.isComplex(null));
        assertFalse(DiffStack.isComplex(""));
        assertFalse(DiffStack.isComplex("a"));
        assertFalse(DiffStack.isComplex(10));
        assertFalse(DiffStack.isComplex(true));
        assertFalse(DiffStack.isComplex(false));
        assertTrue(DiffStack.isComplex(new HashMap<>()));
        assertTrue(DiffStack.isComplex(new ArrayList<>()));
    }
}
