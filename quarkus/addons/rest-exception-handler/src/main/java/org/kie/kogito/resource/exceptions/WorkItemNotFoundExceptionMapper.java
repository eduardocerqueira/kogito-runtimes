package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.kie.kogito.internal.process.runtime.WorkItemNotFoundException;

@Provider
public class WorkItemNotFoundExceptionMapper extends BaseExceptionMapper<WorkItemNotFoundException> {

    @Override
    public Response toResponse(WorkItemNotFoundException exception) {
        return exceptionsHandler.mapException(exception);
    }
}
