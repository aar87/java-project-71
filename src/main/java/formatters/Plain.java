package formatters;

import java.util.List;
import java.util.Map;

public class Plain implements FormatStyle {
    private static final String INDENT_VALUE = "Property ";

    private String prepareValue(Object value) {
        if (value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value.toString();
    }

    @Override
    public String addStart() {
        return "\n";
    }

    @Override
    public String addEnd() {
        return "";
    }

    @Override
    public String add(String key, Object value) {
        return INDENT_VALUE + "'" + key + "' was added with value: " + prepareValue(value) + System.lineSeparator();
    }

    @Override
    public String remove(String key, Object value) {
        return INDENT_VALUE + "'" + key + "' was removed" + System.lineSeparator();
    }

    @Override
    public String update(String key, Object valueFrom, Object valueTo) {
        return INDENT_VALUE + "'" + key + "' was updated."
                + " From " + prepareValue(valueFrom) + " to " + prepareValue(valueTo) + System.lineSeparator();
    }

    @Override
    public String noChange(String key, Object value) {
        return "";
    }

    @Override
    public String finalize(String result) {
        return result.trim();
    }
}
