package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.kie.kogito.process.ProcessInstanceDuplicatedException;

@Provider
public class ProcessInstanceDuplicatedExceptionMapper extends BaseExceptionMapper<ProcessInstanceDuplicatedException> {

    @Override
    public Response toResponse(ProcessInstanceDuplicatedException exception) {
        return exceptionsHandler.mapException(exception);
    }
}
