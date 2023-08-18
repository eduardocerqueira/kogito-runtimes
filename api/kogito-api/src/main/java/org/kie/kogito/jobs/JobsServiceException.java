package org.kie.kogito.jobs;

public class JobsServiceException extends RuntimeException {

    public JobsServiceException(String message) {
        super(message);
    }

    public JobsServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
