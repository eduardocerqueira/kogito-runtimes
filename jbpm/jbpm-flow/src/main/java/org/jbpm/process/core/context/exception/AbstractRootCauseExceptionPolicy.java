package org.jbpm.process.core.context.exception;

public abstract class AbstractRootCauseExceptionPolicy implements ExceptionHandlerPolicy {
    @Override
    public boolean test(String errorCode, Throwable exception) {
        boolean found = verify(errorCode, exception);
        Throwable rootCause = exception.getCause();
        while (!found && rootCause != null) {
            found = verify(errorCode, rootCause);
            rootCause = rootCause.getCause();
        }
        return found;
    }

    protected abstract boolean verify(String errorCode, Throwable exception);

}
