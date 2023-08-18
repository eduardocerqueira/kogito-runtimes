package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.kie.kogito.process.workitem.NotAuthorizedException;

@Provider
public class NotAuthorizedExceptionMapper extends BaseExceptionMapper<NotAuthorizedException> {

    @Override
    public Response toResponse(NotAuthorizedException exception) {
        return exceptionsHandler.mapException(exception);
    }
}
