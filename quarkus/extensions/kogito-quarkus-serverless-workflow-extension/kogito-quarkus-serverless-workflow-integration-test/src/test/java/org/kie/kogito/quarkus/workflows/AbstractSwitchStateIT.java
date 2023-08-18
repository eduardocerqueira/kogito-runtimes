package org.kie.kogito.quarkus.workflows;

import io.restassured.path.json.JsonPath;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractSwitchStateIT {

    protected static final String DECISION_APPROVED = "Approved";
    protected static final String DECISION_DENIED = "Denied";
    protected static final String DECISION_INVALIDATED = "Invalidated";
    protected static final String DECISION_NO_DECISION = "NoDecision";

    private static final String DECISION_PATH = "workflowdata.decision";

    protected static void assertDecision(JsonPath jsonPath, String expectedDecision) {
        String currentDecision = jsonPath.get(DECISION_PATH);
        assertThat(currentDecision).isEqualTo(expectedDecision);
    }

    protected static String buildProcessInput(int age) {
        return "{\"workflowdata\": {\"age\": " + age + "} }";
    }
}
