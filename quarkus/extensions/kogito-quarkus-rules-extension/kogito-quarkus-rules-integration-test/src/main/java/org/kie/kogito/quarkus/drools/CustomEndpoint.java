package org.kie.kogito.quarkus.drools;

import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.MapDataContext;
import org.kie.kogito.incubation.rules.RuleUnitIds;
import org.kie.kogito.incubation.rules.services.RuleUnitService;

@Path("/custom")
public class CustomEndpoint {

    @Inject
    AppRoot appRoot;
    @Inject
    RuleUnitService svc;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<DataContext> hello(Map<String, Object> payload) {
        // path: /rule-units/io.quarkus.it.kogito.drools.AlertingService/queries/Warnings

        var queryId = appRoot.get(RuleUnitIds.class)
                .get("io.quarkus.it.kogito.drools.AlertingService")
                .queries()
                .get("Warnings");
        DataContext ctx = MapDataContext.from(payload);
        return svc.evaluate(queryId, ctx).findFirst();
    }

}
