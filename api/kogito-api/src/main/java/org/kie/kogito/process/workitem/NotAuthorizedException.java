package org.kie.kogito.process.workitem;

/**
 * Thrown when there is security violation, usually due to policy enforcement
 *
 */
public class NotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = -40827773509603874L;

    public NotAuthorizedException(String message) {
        super(message);
    }
}
