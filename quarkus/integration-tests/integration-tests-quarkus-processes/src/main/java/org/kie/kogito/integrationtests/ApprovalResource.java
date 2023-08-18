package org.kie.kogito.integrationtests;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kie.kogito.Model;
import org.kie.kogito.process.Process;

@Path("/approvalsdetails")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApprovalResource {

    @Inject
    @Named("approvals")
    Process<? extends Model> process;

    @GET
    public Response getWorkflowType() {
        return Response.ok().entity(Map.of("type", process.type(), "version", process.version())).build();
    }

}
