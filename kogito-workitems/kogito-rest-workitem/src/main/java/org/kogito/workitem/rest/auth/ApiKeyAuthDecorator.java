package org.kogito.workitem.rest.auth;

import java.util.Map;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import io.vertx.mutiny.ext.web.client.HttpRequest;

import static org.kie.kogito.internal.utils.ConversionUtils.isEmpty;
import static org.kogito.workitem.rest.RestWorkItemHandlerUtils.getParam;

public class ApiKeyAuthDecorator implements AuthDecorator {

    public static final String KEY = "apiKey";
    public static final String KEY_PREFIX = "apiKeyPrefix";

    public enum Location {
        HEADER,
        QUERY,
        COOKIE
    }

    private final String paramName;
    private final Location location;

    public ApiKeyAuthDecorator() {
        this("X-API-KEY", Location.HEADER);
    }

    public ApiKeyAuthDecorator(String paramName, Location location) {
        this.paramName = paramName;
        this.location = location;
    }

    @Override
    public void decorate(KogitoWorkItem item, Map<String, Object> parameters, HttpRequest<?> request) {
        String apiKey = getApiKey(getParam(parameters, KEY_PREFIX, String.class, null), getParam(parameters, KEY, String.class, ""));
        if (!isEmpty(apiKey)) {
            switch (location) {
                case QUERY:
                    request.addQueryParam(paramName, apiKey);
                    break;
                default:
                case HEADER:
                    request.putHeader(paramName, apiKey);
            }
        }
    }

    private static String getApiKey(String apiKeyPrefix, String apiKey) {
        return apiKeyPrefix != null ? apiKeyPrefix + " " + apiKey : apiKey;
    }
}
