package org.jbpm.process.core.context.exception;

import java.util.ArrayList;
import java.util.Collection;

public class ExceptionHandlerPolicyFactory {

    private ExceptionHandlerPolicyFactory() {
    }

    private static Collection<ExceptionHandlerPolicy> policies = new ArrayList<>();

    static {
        policies.add(new ErrorCodeExceptionPolicy());
        policies.add(new IsExceptionPolicy());
        policies.add(new MessageContentEqualsExceptionPolicy());
        policies.add(new IsWrappedExceptionPolicy());
        policies.add(new MessageContentRegexExceptionPolicy());
        policies.add(new IsChildExceptionPolicy());
    }

    public static Collection<ExceptionHandlerPolicy> getHandlerPolicies() {
        return policies;
    }
}
