package formatters;

public interface FormatStyle {
    String addStart();
    String addEnd();
    String add(String key, Object value);
    String remove(String key, Object value);
    String update(String keyFrom, Object valueFrom, Object valueTo);
    String noChange(String key, Object value);
}
