package org.kie.kogito.quarkus.dmn;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.kie.dmn.api.core.DMNResult;
import org.kie.kogito.decision.DecisionModel;
import org.kie.kogito.decision.DecisionModels;

import io.quarkus.test.junit.QuarkusTest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class TrafficViolationTest {

    @Inject
    DecisionModels decisionModels;

    @Test
    public void testEvaluateTrafficViolation() {
        DecisionModel trafficViolation = decisionModels
                .getDecisionModel("https://github.com/kiegroup/drools/kie-dmn/_A4BCA8B8-CF08-433F-93B2-A2598F19ECFF", "Traffic Violation");

        Map<String, Object> driver = new HashMap<>();
        driver.put("Points", 2);

        Map<String, Object> violation = new HashMap<>();
        violation.put("Type", "speed");
        violation.put("Actual Speed", 120);
        violation.put("Speed Limit", 100);

        Map<String, Object> context = new HashMap<>();
        context.put("Driver", driver);
        context.put("Violation", violation);

        DMNResult dmnResult = trafficViolation.evaluateAll(trafficViolation.newContext(context));

        assertThat(dmnResult.getDecisionResultByName("Should the driver be suspended?").getResult())
                .isEqualTo("No");
    }
}
