package org.kie.kogito.event.impl;

import java.util.Objects;

public class TestEvent {

    private String dummyField;

    @SuppressWarnings("unused")
    public TestEvent() {
    }

    public TestEvent(String dummyField) {
        this.dummyField = dummyField;
    }

    @SuppressWarnings("unused")
    public String getDummyField() {
        return dummyField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestEvent)) {
            return false;
        }
        TestEvent that = (TestEvent) o;
        return Objects.equals(getDummyField(), that.getDummyField());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDummyField());
    }
}
