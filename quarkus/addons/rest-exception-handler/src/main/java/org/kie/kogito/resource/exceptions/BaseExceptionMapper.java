package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public abstract class BaseExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {

    protected ExceptionsHandler exceptionsHandler;

    protected BaseExceptionMapper() {
        this.exceptionsHandler = new ExceptionsHandler();
    }

    @Override
    @SuppressWarnings("squid:S3038")
    public abstract Response toResponse(E e);
}
