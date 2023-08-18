package org.kogito.workitem.rest.auth;

import java.util.Map;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import io.vertx.mutiny.ext.web.client.HttpRequest;

import static org.kie.kogito.internal.utils.ConversionUtils.isEmpty;
import static org.kogito.workitem.rest.RestWorkItemHandlerUtils.getParam;

public class BearerTokenAuthDecorator implements AuthDecorator {

    public static final String BEARER_TOKEN = "accessToken";

    @Override
    public void decorate(KogitoWorkItem item, Map<String, Object> parameters, HttpRequest<?> request) {
        String bearerToken = getParam(parameters, BEARER_TOKEN, String.class, null);
        if (!isEmpty(bearerToken)) {
            request.bearerTokenAuthentication(bearerToken);
        }
    }
}
