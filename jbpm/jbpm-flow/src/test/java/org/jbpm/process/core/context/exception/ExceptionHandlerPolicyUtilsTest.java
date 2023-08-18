package org.jbpm.process.core.context.exception;

import org.junit.jupiter.api.Test;

import static org.jbpm.process.core.context.exception.ExceptionHandlerPolicyUtils.isException;
import static org.jbpm.process.core.context.exception.ExceptionHandlerPolicyUtils.isExceptionErrorCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExceptionHandlerPolicyUtilsTest {

    @Test
    void testIsException() {
        assertTrue(isException("java.lang.RuntimeException", RuntimeException.class));
        assertFalse(isException("java.lang.Exception", RuntimeException.class));
        assertFalse(isException("java.lang.runtimeException", RuntimeException.class));
    }

    @Test
    void testIsExceptionClass() {
        assertTrue(isExceptionErrorCode("java.lang.RuntimeException"));
        assertTrue(isExceptionErrorCode("java.lang.Exception"));
        assertFalse(isExceptionErrorCode("pepe@hotmail.com"));
        assertFalse(isExceptionErrorCode("java.lang.runtimeException pepe"));
    }
}
