package org.kie.kogito.quarkus.workflows;

import java.util.Collections;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.Fault;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class ExternalServiceMock implements QuarkusTestResourceLifecycleManager {

    public static final String SUCCESSFUL_QUERY = "SUCCESSFUL_QUERY";
    public static final String GENERATE_ERROR_QUERY = "GENERATE_ERROR_QUERY";

    public static final String EXTERNAL_SERVICE_MOCK_URL = "external-service-mock.url";

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(options().dynamicPort());
        wireMockServer.start();
        configureFor(wireMockServer.port());

        // mock a successful invocation
        stubFor(post("/external-service/sendRequest")
                .withHeader(CONTENT_TYPE, equalTo(APPLICATION_JSON))
                .withRequestBody(equalToJson("{\"query\" : \"" + SUCCESSFUL_QUERY + "\"}"))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withBody("{}")));

        // mock a failing invocation
        stubFor(post("/external-service/sendRequest")
                .withHeader(CONTENT_TYPE, equalTo(APPLICATION_JSON))
                .withRequestBody(equalToJson("{\"query\" : \"" + GENERATE_ERROR_QUERY + "\"}"))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .withFault(Fault.CONNECTION_RESET_BY_PEER)));

        return Collections.singletonMap(EXTERNAL_SERVICE_MOCK_URL, wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
