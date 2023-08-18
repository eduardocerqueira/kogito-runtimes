package org.kogito.workitem.rest.decorators;

import java.util.Map;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import io.vertx.mutiny.ext.web.client.HttpRequest;

public interface RequestDecorator {
    public void decorate(KogitoWorkItem item, Map<String, Object> parameters, HttpRequest<?> request);
}
