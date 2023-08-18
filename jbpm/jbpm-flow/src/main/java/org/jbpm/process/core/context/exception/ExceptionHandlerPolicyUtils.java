package org.jbpm.process.core.context.exception;

import java.util.regex.Pattern;

class ExceptionHandlerPolicyUtils {

    static boolean isException(String errorCode, Class<?> exceptionClass) {
        return exceptionClass.getName().equals(errorCode);
    }

    private static final Pattern classNamePattern = Pattern.compile("([\\p{L}_$][\\p{L}\\p{N}_$]*\\.)*[\\p{L}_$][\\p{L}\\p{N}_$]*");

    static boolean isExceptionErrorCode(String errorCode) {
        return classNamePattern.matcher(errorCode).matches();
    }

    private ExceptionHandlerPolicyUtils() {
    }
}
