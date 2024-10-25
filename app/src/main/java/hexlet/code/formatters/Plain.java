package hexlet.code.formatters;

import hexlet.code.DiffStack;

import java.util.List;
import java.util.Map;

public final class Plain implements FormatStyle {
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
    public String process(List<Map<String, Object>> diffList) {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> diff : diffList) {
            String key = diff.get("key").toString();
            Object from = diff.get("from");
            Object to = diff.get("to");

            String row = "";

            switch (diff.get("state")) {
                case DiffStack.State.UPDATED:
                    row += INDENT_VALUE + "'" + key + "' was updated." + " From " + prepareValue(from);
                    row += " to " + prepareValue(to) + System.lineSeparator();
                    break;
                case DiffStack.State.ADDED:
                    row += INDENT_VALUE + "'" + key + "' was added with value: " + prepareValue(to)
                        + System.lineSeparator();
                    break;
                case DiffStack.State.REMOVED:
                    row += INDENT_VALUE + "'" + key + "' was removed" + System.lineSeparator();
                    break;
                default:
                    row = "";
            }

            result.append(row);
        }

        return result.toString().trim();
    }
}
