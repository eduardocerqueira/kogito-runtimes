package org.jbpm.process.core.context.exception;

import java.util.Objects;

import static org.jbpm.process.core.context.exception.ExceptionHandlerPolicyUtils.isExceptionErrorCode;

public class MessageContentEqualsExceptionPolicy implements ExceptionHandlerPolicy {

    @Override
    public boolean test(String errorCode, Throwable exception) {
        return !isExceptionErrorCode(errorCode) && Objects.equals(errorCode, exception.getMessage());
    }
}
