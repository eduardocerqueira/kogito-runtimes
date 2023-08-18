package org.jbpm.process.instance.context.exception;

import org.jbpm.process.core.context.exception.ExceptionHandler;
import org.jbpm.process.core.context.exception.ExceptionScope;
import org.jbpm.process.instance.context.AbstractContextInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public abstract class ExceptionScopeInstance extends AbstractContextInstance {

    private static final long serialVersionUID = 510l;

    @Override
    public String getContextType() {
        return ExceptionScope.EXCEPTION_SCOPE;
    }

    public ExceptionScope getExceptionScope() {
        return (ExceptionScope) getContext();
    }

    public void handleException(Throwable exception, KogitoProcessContext params) {
        ExceptionHandler handler = getExceptionScope().getExceptionHandler(exception);
        if (handler == null) {
            throw new IllegalArgumentException(
                    "Could not find ExceptionHandler for " + exception);
        }
        handleException(handler, exception.getClass().getCanonicalName(), params);
    }

    public void handleException(String exception, KogitoProcessContext params) {
        ExceptionHandler handler = getExceptionScope().getExceptionHandler(exception);
        if (handler == null) {
            throw new IllegalArgumentException(
                    "Could not find ExceptionHandler for " + exception);
        }
        handleException(handler, exception, params);
    }

    public abstract void handleException(ExceptionHandler handler, String exception, KogitoProcessContext params);

}
