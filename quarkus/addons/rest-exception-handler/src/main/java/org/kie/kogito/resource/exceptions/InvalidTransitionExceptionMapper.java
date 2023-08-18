package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.kie.kogito.process.workitem.InvalidTransitionException;

@Provider
public class InvalidTransitionExceptionMapper extends BaseExceptionMapper<InvalidTransitionException> {

    @Override
    public Response toResponse(InvalidTransitionException exception) {
        return exceptionsHandler.mapException(exception);
    }
}
