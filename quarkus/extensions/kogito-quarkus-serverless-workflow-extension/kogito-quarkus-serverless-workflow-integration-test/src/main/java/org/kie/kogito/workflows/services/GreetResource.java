package org.kie.kogito.workflows.services;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kie.kogito.Model;
import org.kie.kogito.process.Process;

@Path("/greetdetails")
public class GreetResource {

    @Inject
    @Named("greet")
    Process<? extends Model> process;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWorkflowType() {
        return Response.ok().entity(Map.of("type", process.type(), "version", process.version())).build();
    }

}
