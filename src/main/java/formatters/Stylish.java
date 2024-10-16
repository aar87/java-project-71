package formatters;


public class Stylish implements FormatStyle {
    private static final String INDENT_VALUE = "  ";

    private String cleanValue(Object value) {
        return String.valueOf(value);
    }

    @Override
    public String addStart() {
        return "{" + System.lineSeparator();
    }

    @Override
    public String addEnd() {
        return "}";
    }

    @Override
    public String add(String key, Object value) {
        return INDENT_VALUE + "+ " + key + ": " + cleanValue(value) + System.lineSeparator();
    }

    @Override
    public String remove(String key, Object value) {
        return INDENT_VALUE + "- " + key + ": " + cleanValue(value) + System.lineSeparator();
    }

    @Override
    public String update(String key, Object fromValue, Object toValue) {
        return remove(key, fromValue) + add(key, toValue);
    }

    @Override
    public String noChange(String key, Object value) {
        return INDENT_VALUE + "  " + key + ": " + cleanValue(value) + System.lineSeparator();
    }

    @Override
    public String finalize(String result) {
        return result.trim();
    }
}
