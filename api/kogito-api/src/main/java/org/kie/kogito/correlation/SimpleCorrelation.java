package org.kie.kogito.correlation;

import java.util.Objects;
import java.util.StringJoiner;

public class SimpleCorrelation<T> implements Correlation<T> {

    private String key;
    private T value;

    public SimpleCorrelation() {
    }

    public SimpleCorrelation(String key) {
        this(key, null);
    }

    public SimpleCorrelation(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleCorrelation)) {
            return false;
        }
        SimpleCorrelation that = (SimpleCorrelation) o;
        return Objects.equals(getKey(), that.getKey()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleCorrelation.class.getSimpleName() + "[", "]")
                .add("key='" + key + "'")
                .add("value=" + value)
                .toString();
    }
}
