package hexlet.code;

import formatters.Stylish;
import formatters.Plain;
import formatters.Json;
import formatters.Formatter;
import org.junit.jupiter.api.Test;

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
}
