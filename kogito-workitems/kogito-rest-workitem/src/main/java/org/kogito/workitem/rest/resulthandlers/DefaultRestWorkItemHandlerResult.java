package org.kogito.workitem.rest.resulthandlers;

import java.util.Map;

import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.HttpResponse;

public class DefaultRestWorkItemHandlerResult implements RestWorkItemHandlerResult {

    @Override
    public Object apply(HttpResponse<Buffer> response, Class<?> target) {
        return target == null ? response.bodyAsJson(Map.class) : response.bodyAsJson(target);
    }
}
