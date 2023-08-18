package org.kie.kogito.integrationtests.quarkus;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;

import static org.kie.kogito.integrationtests.quarkus.CommonTestUtils.testDescriptive;
import static org.kie.kogito.integrationtests.quarkus.CommonTestUtils.testResult;

@Disabled
@QuarkusIntegrationTest
class PMMLTreeIT {

    private static final String BASE_PATH = "/PMMLTree/SampleMine";
    private static final String TARGET = "decision";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void testEvaluateSampleMineResult() {
        String inputData = "{\"temperature\":30.0, \"humidity\":10.0}";
        testResult(inputData, BASE_PATH, TARGET, "sunglasses");
    }

    @Test
    void testEvaluateSampleMineDescriptive() {
        String inputData = "{\"temperature\":30.0, \"humidity\":10.0}";
        final Map<String, Object> expectedResultMap = new HashMap<>();
        expectedResultMap.put(TARGET, "sunglasses");
        expectedResultMap.put("weatherdecision", "sunglasses");
        testDescriptive(inputData, BASE_PATH, TARGET, expectedResultMap);
    }

}
