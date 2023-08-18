package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.kie.kogito.process.ProcessInstanceNotFoundException;

@Provider
public class ProcessInstanceNotFoundExceptionMapper extends BaseExceptionMapper<ProcessInstanceNotFoundException> {

    @Override
    public Response toResponse(ProcessInstanceNotFoundException exception) {
        return exceptionsHandler.mapException(exception);
    }
}
