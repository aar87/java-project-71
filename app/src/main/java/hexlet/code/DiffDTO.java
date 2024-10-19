package hexlet.code;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class DiffDTO {
    private DiffType state;
    private final String key;
    private final Object fromValue;
    private final Object toValue;
}
