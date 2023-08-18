package org.kie.kogito.event;

import java.util.function.Function;

public interface EventFactory<V> extends Function<String, V>, AutoCloseable, Comparable<EventFactory<V>> {

    default int ordinal() {
        return 100;
    }

    default void ready() {
    }

    @Override
    default int compareTo(EventFactory<V> o) {
        return ordinal() - o.ordinal();
    }

    @Override
    default void close() throws Exception {
    }
}
