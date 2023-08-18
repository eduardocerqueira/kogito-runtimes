package org.kie.kogito.event.cloudevents.utils;

import java.util.Collections;
import java.util.List;

public final class InvalidCloudEventException extends RuntimeException {

    private final List<String> errors;

    public InvalidCloudEventException(List<String> errors) {
        this.errors = Collections.unmodifiableList(errors);
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        return "Invalid CloudEvent: " + System.lineSeparator() + String.join(System.lineSeparator(), errors);
    }
}
