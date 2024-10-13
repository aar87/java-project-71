package formatters;

public class Formatter {
    public static final String STYLISH_FORMATTER = "stylish";
    public static final String PLAIN_FORMATTER = "plain";

    public static FormatStyle build(String format) {
        if (format.equals(STYLISH_FORMATTER)) {
            return new Stylish();
        } else if (format.equals(PLAIN_FORMATTER)) {
            return new Plain();
        }

        throw new IllegalArgumentException("Unknown format: " + format);
    }
}
