package formatters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json implements FormatStyle {
    @Override
    public String addStart() {
        return "{";
    }

    @Override
    public String addEnd() {
        return "}";
    }

    @Override
    public String add(String key, Object value) {
        return "\"" + key + "\":" + "\"+ " + value.toString() + "\",";
    }

    @Override
    public String remove(String key, Object value) {
        return "\"" + key + "\":" + "\"- " + value.toString() + "\",";
    }

    @Override
    public String update(String keyFrom, Object valueFrom, Object valueTo) {
        return "";
    }

    @Override
    public String noChange(String key, Object value) {
        return "";
    }

    @Override
    public String finalize(String result) throws IOException {
        result = result.trim().replace(",}", "}");

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(result);
        JsonNode actualObj = mapper.readTree(parser);

        return actualObj.toPrettyString();
    }
}
