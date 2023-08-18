package org.jbpm.process.core.context.exception;

import static org.jbpm.process.core.context.exception.ExceptionHandlerPolicyUtils.isException;

public class IsWrappedExceptionPolicy extends AbstractRootCauseExceptionPolicy {

    @Override
    public boolean verify(String errorCode, Throwable exception) {
        return isException(errorCode, exception.getClass());
    }
}
