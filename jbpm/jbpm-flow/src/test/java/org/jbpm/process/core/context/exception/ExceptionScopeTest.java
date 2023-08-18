package org.jbpm.process.core.context.exception;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionScopeTest {
    @Test
    void testMatchingPolicies() {
        ExceptionScope exceptionScope = new ExceptionScope();
        ExceptionHandler handler1 = Mockito.mock(ExceptionHandler.class);
        ExceptionHandler handler2 = Mockito.mock(ExceptionHandler.class);
        ExceptionHandler handler3 = Mockito.mock(ExceptionHandler.class);
        exceptionScope.setExceptionHandler(IOException.class.getCanonicalName(), handler1);
        exceptionScope.setExceptionHandler(RuntimeException.class.getCanonicalName(), handler2);
        exceptionScope.setExceptionHandler(Exception.class.getCanonicalName(), handler3);
        assertEquals(handler1, exceptionScope.getHandlerFromPolicies(new IOException()));
        assertEquals(handler2, exceptionScope.getHandlerFromPolicies(new RuntimeException(new IOException())));
        assertEquals(handler3, exceptionScope.getHandlerFromPolicies(new Exception(new RuntimeException())));
    }
}
