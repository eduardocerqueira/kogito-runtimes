package org.kie.kogito.quarkus.pmml;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.kie.api.pmml.PMML4Result;
import org.kie.kogito.prediction.PredictionModel;
import org.kie.kogito.prediction.PredictionModels;

import io.quarkus.test.junit.QuarkusTest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class PMMLRegressionTest {

    @Inject
    PredictionModels predictionModels;

    @Test
    void testEvaluateLinRegResult() {
        PredictionModel linReg = predictionModels.getPredictionModel("PMMLRegression.pmml", "LinReg");

        Map<String, Object> context = new HashMap<>();
        context.put("fld1", 3.0);
        context.put("fld2", 2.0);
        context.put("fld3", "y");

        PMML4Result pmml4Result = linReg.evaluateAll(linReg.newContext(context));

        double fld4 = (double) pmml4Result.getResultVariables().get("fld4");
        assertThat(fld4).isEqualTo(52.5d, Offset.offset(0.1d));
    }
}
