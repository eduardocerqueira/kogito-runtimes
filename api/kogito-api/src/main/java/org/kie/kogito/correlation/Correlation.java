package org.kie.kogito.correlation;

import java.util.Optional;

public interface Correlation<V> extends Comparable<Correlation<V>> {

    String getKey();

    V getValue();

    default String asString() {
        return Optional.ofNullable(getValue()).map(Object::toString).orElse(null);
    }

    @Override
    default int compareTo(Correlation<V> other) {
        return getKey().compareTo(other.getKey());
    }
}
