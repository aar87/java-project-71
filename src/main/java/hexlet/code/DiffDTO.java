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
    private final String fromValue;
    private final String toValue;
    private boolean isComplex;
}
