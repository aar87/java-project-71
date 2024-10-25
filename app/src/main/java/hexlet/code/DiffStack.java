package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public final class DiffStack {
    public enum State {
        UNCHANGED,
        UPDATED,
        ADDED,
        REMOVED,
    }

    public static Boolean safeCompare(Object first, Object second) {
        if (first == second) {
            return true;
        }

        if (first == null || second == null) {
            return false;
        }

        return first.equals(second);
    }

    public static Boolean isComplex(Object value) {
        return value instanceof Map || value instanceof List;
    }

    public static List<Map<String, Object>> create(String[] keys, Map<String, Object> from, Map<String, Object> to) {
        List<Map<String, Object>> diffStack = new ArrayList<>();
        for (String key: keys) {
            Object fromValue = from.get(key);
            Object toValue = to.get(key);
            State state = State.UNCHANGED;

            if (from.containsKey(key) && to.containsKey(key) && !safeCompare(fromValue, toValue)) {
                state = State.UPDATED;
            } else if (to.containsKey(key) && !from.containsKey(key)) {
                state = State.ADDED;
            } else if (from.containsKey(key) && !to.containsKey(key)) {
                state = State.REMOVED;
            }

            diffStack.add(getNode(key, fromValue, toValue, state));
        }

        return diffStack;
    }

    public static Map<String, Object> getNode(String key, Object from, Object to, State state) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("state", state);
        map.put("fromIsComplex", isComplex(from));
        map.put("toIsComplex", isComplex(to));
        map.put("from", from);
        map.put("to", to);
        return map;
    }
}
