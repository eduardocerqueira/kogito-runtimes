package org.kogito.workitem.rest.auth;

import java.util.Map;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import io.vertx.mutiny.ext.web.client.HttpRequest;

public abstract class OAuth2AuthDecorator implements AuthDecorator {
    private final TokenRetriever tokenRetriever;

    protected OAuth2AuthDecorator(TokenRetriever tokenRetriever) {
        this.tokenRetriever = tokenRetriever;
    }

    @Override
    public void decorate(KogitoWorkItem item, Map<String, Object> parameters, HttpRequest<?> request) {
        request.bearerTokenAuthentication(tokenRetriever.getToken(parameters));
    }
}
