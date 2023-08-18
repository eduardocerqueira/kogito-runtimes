package org.jbpm.process.core.context.exception;

import static org.jbpm.process.core.context.exception.ExceptionHandlerPolicyUtils.isException;

public class IsChildExceptionPolicy extends AbstractRootCauseExceptionPolicy {

    @Override
    public boolean verify(String errorCode, Throwable exception) {
        Class<?> exceptionClass = exception.getClass().getSuperclass();
        boolean found = false;
        while (!found && !exceptionClass.equals(Object.class)) {
            found = isException(errorCode, exceptionClass);
            exceptionClass = exceptionClass.getSuperclass();
        }
        return found;
    }
}
