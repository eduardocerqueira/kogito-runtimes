package org.kie.kogito.process.validation;

import java.util.Map;

public abstract class ValidationDecorator {

    protected final Map<String, Throwable> exceptions;

    protected ValidationDecorator(Map<String, Throwable> exceptions) {
        this.exceptions = exceptions;
    }

    public abstract void decorate();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        exceptions.forEach((processId, exception) -> sb.append(String.format("Invalid process: '%s'. Found error: %s%n", processId, exception)));
        return sb.toString();
    }
}
