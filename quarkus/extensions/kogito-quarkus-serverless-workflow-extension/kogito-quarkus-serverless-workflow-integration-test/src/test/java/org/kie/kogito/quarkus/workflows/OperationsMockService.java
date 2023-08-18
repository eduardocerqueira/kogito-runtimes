package org.kie.kogito.quarkus.workflows;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class OperationsMockService implements QuarkusTestResourceLifecycleManager {

    private static WireMockServer subtractionService;
    private static WireMockServer multiplicationService;

    public static final String SUBTRACTION_SERVICE_MOCK_URL = "subtraction-service-mock.url";
    public static final String MULTIPLICATION_SERVICE_MOCK_URL = "multiplication-service-mock.url";

    @Override
    public Map<String, String> start() {
        multiplicationService =
                startServer("{  \"product\": 37.808 }", p -> p.withHeader("pepe", new EqualToPattern("pepa")));
        subtractionService =
                startServer("{ \"difference\": 68.0 }", p -> p);

        Map<String, String> result = new HashMap<>();
        result.put(MULTIPLICATION_SERVICE_MOCK_URL, multiplicationService.baseUrl());
        result.put(SUBTRACTION_SERVICE_MOCK_URL, subtractionService.baseUrl());
        return result;
    }

    @Override
    public void stop() {
        if (multiplicationService != null) {
            multiplicationService.stop();
        }
        if (subtractionService != null) {
            subtractionService.stop();
        }
    }

    private static WireMockServer startServer(final String response, UnaryOperator<MappingBuilder> function) {
        final WireMockServer server = new WireMockServer(options().dynamicPort());
        server.start();
        server.stubFor(function.apply(post(urlEqualTo("/")))
                .withPort(server.port())
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        return server;
    }
}
