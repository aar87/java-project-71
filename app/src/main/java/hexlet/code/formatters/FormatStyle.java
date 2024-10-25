package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface FormatStyle {
    String process(List<Map<String, Object>> diffList) throws JsonProcessingException;
}
