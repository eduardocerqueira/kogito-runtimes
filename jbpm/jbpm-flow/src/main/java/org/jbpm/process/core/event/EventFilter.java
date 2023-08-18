package org.jbpm.process.core.event;

import java.util.function.Function;

public interface EventFilter {

    default boolean isCorrelated() {
        return false;
    }

    boolean acceptsEvent(String type, Object event, Function<String, Object> resolver);

}
