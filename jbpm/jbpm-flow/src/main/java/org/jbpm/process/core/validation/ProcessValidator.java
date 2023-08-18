package org.jbpm.process.core.validation;

import java.util.Arrays;
import java.util.Optional;

import org.kie.api.definition.process.Process;
import org.kie.api.io.Resource;
import org.kie.kogito.process.validation.ValidationException;

/**
 * A validator for validating a RuleFlow process.
 * 
 */
public interface ProcessValidator {

    ProcessValidationError[] validateProcess(Process process);

    boolean accept(Process process, Resource resource);

    boolean compilationSupported();

    default void validate(Process process) throws ValidationException {
        final ProcessValidationError[] errors = validateProcess(process);
        Optional.ofNullable(errors)
                .filter(e -> e.length == 0)
                .orElseThrow(() -> new ValidationException(process.getId(), Arrays.asList(errors)));
    }
}
