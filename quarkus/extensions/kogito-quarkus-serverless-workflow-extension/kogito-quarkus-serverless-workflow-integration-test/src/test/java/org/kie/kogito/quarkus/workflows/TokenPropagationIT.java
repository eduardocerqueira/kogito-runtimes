package org.kie.kogito.quarkus.workflows;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.path.json.JsonPath;

import static org.kie.kogito.quarkus.workflows.ExternalServiceMock.SUCCESSFUL_QUERY;
import static org.kie.kogito.quarkus.workflows.TokenPropagationExternalServicesMock.AUTHORIZATION_TOKEN;
import static org.kie.kogito.quarkus.workflows.TokenPropagationExternalServicesMock.SERVICE3_AUTHORIZATION_TOKEN;
import static org.kie.kogito.quarkus.workflows.TokenPropagationExternalServicesMock.SERVICE3_HEADER_TO_PROPAGATE;
import static org.kie.kogito.quarkus.workflows.TokenPropagationExternalServicesMock.SERVICE4_AUTHORIZATION_TOKEN;
import static org.kie.kogito.quarkus.workflows.TokenPropagationExternalServicesMock.SERVICE4_HEADER_TO_PROPAGATE;
import static org.kie.kogito.test.utils.ProcessInstancesRESTTestUtils.newProcessInstance;

@QuarkusTestResource(TokenPropagationExternalServicesMock.class)
@QuarkusTestResource(KeycloakServiceMock.class)
@QuarkusIntegrationTest
class TokenPropagationIT {

    @Test
    void tokenPropagations() {
        // start a new process instance by sending the post query and collect the process instance id.
        String processInput = buildProcessInput(SUCCESSFUL_QUERY);
        Map<String, String> headers = new HashMap<>();
        // prepare the headers to pass to the token_propagation SW.
        // service token-propagation-external-service1 and token-propagation-external-service2 will receive the AUTHORIZATION_TOKEN 
        headers.put(HttpHeaders.AUTHORIZATION, AUTHORIZATION_TOKEN);
        // service token-propagation-external-service3 will receive the SERVICE3_AUTHORIZATION_TOKEN
        headers.put(SERVICE3_HEADER_TO_PROPAGATE, SERVICE3_AUTHORIZATION_TOKEN);
        // service token-propagation-external-service4 will receive the SERVICE4_AUTHORIZATION_TOKEN
        headers.put(SERVICE4_HEADER_TO_PROPAGATE, SERVICE4_AUTHORIZATION_TOKEN);

        JsonPath jsonPath = newProcessInstance("/token_propagation", processInput, headers);
        Assertions.assertThat(jsonPath.getString("id")).isNotBlank();
    }

    protected static String buildProcessInput(String query) {
        return "{\"workflowdata\": {\"query\": \"" + query + "\"} }";
    }
}
