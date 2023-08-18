package org.kie.kogito.quarkus.dmn;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.MapDataContext;
import org.kie.kogito.incubation.decisions.DecisionIds;
import org.kie.kogito.incubation.decisions.services.DecisionService;

@Path("/custom")
public class CustomEndpoint {

    @Inject
    AppRoot appRoot;
    @Inject
    DecisionService svc;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DataContext hello(Map<String, Object> payload) {
        // path: /decisions/https%3A%2F%2Fgithub.com%2Fkiegroup%2Fdrools%2Fkie-dmn%2F_A4BCA8B8-CF08-433F-93B2-A2598F19ECFF/Traffic%20Violation

        var id = appRoot
                .get(DecisionIds.class)
                .get("http://www.trisotech.com/definitions/_2ceee5b6-0f0d-41ef-890e-2cd6fb1adb10",
                        "dmnModel")
        // .services()
        // .get("my-service-id")
        ;
        var p = MapDataContext.from(payload);
        return svc.evaluate(id, p);
    }

}
