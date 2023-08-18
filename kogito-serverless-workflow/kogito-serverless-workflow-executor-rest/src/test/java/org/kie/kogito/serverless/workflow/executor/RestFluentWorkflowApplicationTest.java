package org.kie.kogito.serverless.workflow.executor;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.serverless.workflow.fluent.FunctionBuilder.HttpMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import io.serverlessworkflow.api.Workflow;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.serverless.workflow.fluent.ActionBuilder.call;
import static org.kie.kogito.serverless.workflow.fluent.FunctionBuilder.rest;
import static org.kie.kogito.serverless.workflow.fluent.StateBuilder.operation;
import static org.kie.kogito.serverless.workflow.fluent.WorkflowBuilder.workflow;

class RestFluentWorkflowApplicationTest {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort().dynamicHttpsPort())
            .build();

    @Test
    void httpsInvocation() {
        final String FUNCTION_NAME = "function";
        executeFlow(workflow("HelloRest").function(rest(FUNCTION_NAME, HttpMethod.get, "https://localhost:" + wm.getHttpsPort() + "/name"))
                .start(operation().action(call(FUNCTION_NAME))).end().build());
    }

    @Test
    void httpInvocation() {
        final String FUNCTION_NAME = "function";
        executeFlow(workflow("HelloRest").function(rest(FUNCTION_NAME, HttpMethod.get, "http://localhost:" + wm.getPort() + "/name"))
                .start(operation().action(call(FUNCTION_NAME))).end().build());
    }

    private void executeFlow(Workflow workflow) {
        JsonNode expectedOutput = ObjectMapperFactory.get().createObjectNode().put("name", "Javierito");
        wm.stubFor(get("/name").willReturn(aResponse().withStatus(200).withJsonBody(expectedOutput)));
        try (StaticWorkflowApplication application = StaticWorkflowApplication.create()) {
            assertThat(application.execute(workflow, Collections.emptyMap()).getWorkflowdata()).isEqualTo(expectedOutput);
        }
    }

}
