package org.jbpm.process.core.context.exception;

import java.util.function.BiPredicate;

public interface ExceptionHandlerPolicy extends BiPredicate<String, Throwable> {

}
