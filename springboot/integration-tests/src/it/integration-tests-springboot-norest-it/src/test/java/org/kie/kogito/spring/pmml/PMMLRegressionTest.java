package org.kie.kogito.spring.pmml;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.api.pmml.PMML4Result;
import org.kie.kogito.examples.KogitoSpringbootApplication;
import org.kie.kogito.prediction.PredictionModel;
import org.kie.kogito.prediction.PredictionModels;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
class PMMLRegressionTest {

    @Autowired
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
