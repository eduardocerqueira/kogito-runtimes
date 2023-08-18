package org.kie.kogito.addons.k8s.workitems;

public class EndpointCallerException extends RuntimeException {

    public EndpointCallerException(final String message, final Throwable exception) {
        super(message, exception);
    }

    public EndpointCallerException(final String message) {
        super(message);
    }

    public EndpointCallerException(final Throwable exception) {
        super(exception);
    }

}
