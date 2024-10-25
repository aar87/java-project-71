package hexlet.code.formatters;


import hexlet.code.DiffStack;

import java.util.List;
import java.util.Map;

public final class Stylish implements FormatStyle {
    private static final String INDENT_VALUE = " ";

    private String getRow(String sign, String key, String value) {
        return INDENT_VALUE.repeat(2) + sign + INDENT_VALUE + key + ":" + INDENT_VALUE + value + System.lineSeparator();
    }

    @Override
    public String process(List<Map<String, Object>> diffList) {
        StringBuilder result = new StringBuilder();
        result.append("{").append(System.lineSeparator());

        for (Map<String, Object> diff : diffList) {
            String key = diff.get("key").toString();
            String from = String.valueOf(diff.get("from"));
            String to = String.valueOf(diff.get("to"));

            String row = switch (diff.get("state")) {
                case DiffStack.State.UPDATED -> getRow("-", key, from) + getRow("+", key, to);
                case DiffStack.State.ADDED -> getRow("+", key, to);
                case DiffStack.State.REMOVED -> getRow("-", key, from);
                default -> getRow(" ", key, from);
            };

            result.append(row);
        }

        return result.append("}").toString();
    }
}
