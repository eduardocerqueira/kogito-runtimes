package org.kie.kogito.quarkus.pmml;

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
import org.kie.kogito.incubation.predictions.PredictionIds;
import org.kie.kogito.incubation.predictions.services.PredictionService;

@Path("/custom")
public class CustomEndpoint {

    @Inject
    AppRoot appRoot;
    @Inject
    PredictionService svc;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DataContext hello(Map<String, Object> payload) {
        // path: /predictions/LinReg

        var id = appRoot.get(PredictionIds.class).get("PMMLRegression.pmml", "LinReg");
        return svc.evaluate(id, MapDataContext.from(payload));
    }

}