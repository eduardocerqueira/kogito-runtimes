package org.jbpm.process.core.validation.impl;

import org.jbpm.process.core.validation.ProcessValidationError;
import org.kie.api.definition.process.Process;

public class ProcessValidationErrorImpl implements ProcessValidationError {

    private Process process;
    private String message;

    public ProcessValidationErrorImpl(Process process, String message) {
        this.process = process;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Process getProcess() {
        return process;
    }

    public String toString() {
        return "Process '" + process.getName() + "' [" + process.getId() + "]: " + getMessage();
    }

}
