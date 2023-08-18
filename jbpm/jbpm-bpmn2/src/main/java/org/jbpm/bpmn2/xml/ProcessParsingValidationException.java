package org.jbpm.bpmn2.xml;

import java.util.Arrays;

import org.kie.kogito.process.validation.ValidationException;

public class ProcessParsingValidationException extends ValidationException {

    //TODO: inject processId or fileName to identify the the process
    public ProcessParsingValidationException(String message) {
        super(null, Arrays.asList(() -> message));
    }

    public ProcessParsingValidationException(String processId, String message) {
        super(processId, Arrays.asList(() -> message));
    }
}
