package org.kie.kogito.process.validation;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {

    private final String processId;
    private final Collection<ValidationError> errors;

    public ValidationException(String processId, Collection<? extends ValidationError> errors) {
        this.processId = processId;
        this.errors = Collections.unmodifiableCollection(errors);
    }

    public ValidationException(String processId, ValidationError error) {
        this(processId, Collections.singleton(error));
    }

    public ValidationException(String processId, String errorMessage) {
        this(processId, Collections.singleton(() -> errorMessage));
    }

    public Collection<ValidationError> getErrors() {
        return errors;
    }

    public String getProcessId() {
        return processId;
    }

    @Override
    public String getMessage() {
        return errors.stream().map(ValidationError::getMessage).collect(Collectors.joining(" "));
    }
}
