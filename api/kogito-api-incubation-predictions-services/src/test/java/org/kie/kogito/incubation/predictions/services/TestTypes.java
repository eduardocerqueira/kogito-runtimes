package org.kie.kogito.incubation.predictions.services;

import org.junit.jupiter.api.Test;
import org.kie.kogito.incubation.application.ReflectiveAppRoot;
import org.kie.kogito.incubation.common.*;
import org.kie.kogito.incubation.predictions.LocalPredictionId;
import org.kie.kogito.incubation.predictions.PredictionIds;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTypes {
    public static class MyDataContext implements DataContext, DefaultCastable {
        int someParam;
    }

    @Test
    public void testDecisionEvaluationService() {

        // let's just make the compiler happy
        PredictionService svc = new PredictionService() {
            @Override
            public ExtendedDataContext evaluate(LocalId id, DataContext ctx) {
                // ... parses and resolves the id ...
                // .. then evaluates ...
                return ExtendedDataContext.ofData(ctx);
            }
        };
        MapDataContext ctx = MapDataContext.create();

        String fileNameNoSuffix = "somePrediction";
        String fileName = fileNameNoSuffix + ".pmml";

        String modelName = "/mypath/to/" + fileName;

        ReflectiveAppRoot appRoot = new ReflectiveAppRoot();

        // LocalPredictionId decisionId = new LocalPredictionId(modelName);
        LocalPredictionId decisionId = appRoot.get(PredictionIds.class).get(fileNameNoSuffix, modelName);

        assertThat(decisionId.toLocalId().asLocalUri().path()).isEqualTo("/predictions" +
                "/%2Fmypath%2Fto%2FsomePrediction.pmml");

        // set a context using a Map-like interface
        ctx.set("someParam", 1);

        // evaluate the process
        DataContext result =
                svc.evaluate(decisionId, ctx);

        // bind the data in the result to a typed bean
        MyDataContext mdc = result.as(MyDataContext.class);
        assertThat(mdc.someParam).isOne();

    }
}
