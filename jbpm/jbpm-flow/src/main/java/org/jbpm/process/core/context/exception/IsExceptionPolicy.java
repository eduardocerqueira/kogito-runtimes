package org.jbpm.process.core.context.exception;

import static org.jbpm.process.core.context.exception.ExceptionHandlerPolicyUtils.isException;

public class IsExceptionPolicy implements ExceptionHandlerPolicy {
    @Override
    public boolean test(String errorCode, Throwable exception) {
        return isException(errorCode, exception.getClass());
    }
}
