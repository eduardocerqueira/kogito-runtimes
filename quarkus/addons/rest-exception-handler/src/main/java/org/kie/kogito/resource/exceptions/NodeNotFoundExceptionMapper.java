package org.kie.kogito.resource.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.kie.kogito.process.NodeNotFoundException;

@Provider
public class NodeNotFoundExceptionMapper extends BaseExceptionMapper<NodeNotFoundException> {

    @Override
    public Response toResponse(NodeNotFoundException exception) {
        return exceptionsHandler.mapException(exception);
    }
}
