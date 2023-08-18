package org.kie.kogito.internal.process.event;

public interface KogitoObjectListener {

    void beforeValueChanged(Object container, String property, Object oldValue, Object newValue);

    void afterValueChanged(Object container, String property, Object oldValue, Object newValue);
}
