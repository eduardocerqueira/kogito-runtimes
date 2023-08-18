package org.kie.kogito.process;

public interface ProcessError {

    String failedNodeId();

    String errorMessage();

    default Throwable errorCause() {
        return null;
    }

    void retrigger();

    void skip();
}
