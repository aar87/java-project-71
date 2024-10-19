package formatters;

import hexlet.code.DiffDTO;
import hexlet.code.DiffType;

import java.io.IOException;
import java.util.ArrayList;

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

    public static String format(ArrayList<DiffDTO> diffs, FormatStyle formatter) throws IOException {
        StringBuilder result = new StringBuilder();

        result.append(formatter.addStart());

        for (DiffDTO diff : diffs) {
            if (diff.getState() == DiffType.ADDED) {
                result.append(formatter.add(diff.getKey(), diff.getToValue()));
            } else if (diff.getState() == DiffType.REMOVED) {
                result.append(formatter.remove(diff.getKey(), diff.getFromValue()));
            } else if (diff.getState() == DiffType.UPDATED) {
                result.append(formatter.update(diff.getKey(), diff.getFromValue(), diff.getToValue()));
            } else {
                result.append(formatter.noChange(diff.getKey(), diff.getFromValue()));
            }
        }

        result.append(formatter.addEnd());

        return formatter.finalize(result.toString());
    }
}
