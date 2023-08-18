package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.kie.kogito.process.VariableViolationException;

@Provider
public class VariableViolationExceptionMapper extends BaseExceptionMapper<VariableViolationException> {

    @Override
    public Response toResponse(VariableViolationException exception) {
        return exceptionsHandler.mapException(exception);
    }
}
