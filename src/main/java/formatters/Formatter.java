package formatters;

public class Formatter {
    public static final String STYLISH_FORMATTER = "stylish";
    public static final String PLAIN_FORMATTER = "plain";
    public static final String JSON_FORMATTER = "json";

    public static FormatStyle build(String format) {
        return switch (format) {
            case PLAIN_FORMATTER -> new Plain();
            case JSON_FORMATTER -> new Json();
            default -> new Stylish();
        };
    }
}
