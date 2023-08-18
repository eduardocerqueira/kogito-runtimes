package org.kie.kogito.event.impl;

import org.kie.kogito.event.EventMarshaller;

public class NoOpEventMarshaller implements EventMarshaller<Object> {

    @Override
    public <T> Object marshall(T event) {
        return event;
    }
}
