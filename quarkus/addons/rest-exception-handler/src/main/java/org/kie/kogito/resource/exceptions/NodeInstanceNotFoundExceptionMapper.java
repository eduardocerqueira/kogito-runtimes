package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.kie.kogito.process.NodeInstanceNotFoundException;

@Provider
public class NodeInstanceNotFoundExceptionMapper extends BaseExceptionMapper<NodeInstanceNotFoundException> {

    @Override
    public Response toResponse(NodeInstanceNotFoundException exception) {
        return exceptionsHandler.mapException(exception);
    }
}
