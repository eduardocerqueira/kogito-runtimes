package org.kie.kogito.quarkus.workflows;

import java.util.Collections;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.badRequest;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class PerfectSquareServiceMock implements QuarkusTestResourceLifecycleManager {

    public static final String PERFECT_SQUARE_MOCK_PORT = "perfect-square-mock.port";

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(options().dynamicPort());
        wireMockServer.start();
        int port = wireMockServer.port();
        configureFor(port);

        // mock a successful invocation
        stubFor(post("/publish/odd/1")
                .willReturn(badRequest()));

        stubFor(post("/publish/even/4")
                .willReturn(aResponse().withBody("{\"perfect\":true}")));

        stubFor(post("/publish/even/6")
                .willReturn(aResponse().withBody("{\"perfect\":false}")));
        return Collections.singletonMap("kogito.sw.functions.publishPerfectSquare.port", Integer.toString(port));
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
