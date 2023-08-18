package org.kie.kogito.event.impl;

import org.kie.kogito.event.process.ProcessDataEvent;

public class TestCloudEvent<T> extends ProcessDataEvent<T> {

    public TestCloudEvent() {
    }

    public TestCloudEvent(T dummyEvent, String type) {
        this(dummyEvent, type, null);
    }

    public TestCloudEvent(T dummyEvent, String type, String source) {
        this(dummyEvent, type, source, null);
    }

    public TestCloudEvent(T dummyEvent, String type, String source, String referenceId) {
        super(type, source == null ? "KogitoTest" : source, dummyEvent, "1", "1", "1", "1", "1", "1", "1", null, null, referenceId, null);
    }
}
