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
        String row = "{\"state\":" + "\"add\", \"value\": \"" + value + "\"}";
        return "\"" + key + "\":" + row + ",";
    }

    @Override
    public String remove(String key, Object value) {
        String row = "{\"state\":" + "\"remove\", \"value\": \"" + value + "\"}";
        return "\"" + key + "\":" + row + ",";
    }

    @Override
    public String update(String keyFrom, Object valueFrom, Object valueTo) {
        String row = "{\"state\":" + "\"update\", \"from\": \"" + valueFrom + "\"" + ", \"to\": \"" + valueTo + "\"}";
        return "\"" + keyFrom + "\":" + row + ",";
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
