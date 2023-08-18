package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.kie.kogito.process.ProcessInstanceExecutionException;

@Provider
public class ProcessInstanceExecutionExceptionMapper extends BaseExceptionMapper<ProcessInstanceExecutionException> {

    @Override
    public Response toResponse(ProcessInstanceExecutionException exception) {
        return exceptionsHandler.mapException(exception);
    }
}
