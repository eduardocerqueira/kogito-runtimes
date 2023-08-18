package org.kie.kogito.process;

public interface Signal<T> {

    String channel();

    T payload();

    String referenceId();
}
