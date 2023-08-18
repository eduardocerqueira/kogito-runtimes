package org.kogito.workitem.rest.decorators;

import java.util.Map;

import org.kie.kogito.event.cloudevents.extension.ProcessMeta;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import io.vertx.mutiny.ext.web.client.HttpRequest;

public class HeaderMetadataDecorator implements RequestDecorator {

    @Override
    public void decorate(KogitoWorkItem item, Map<String, Object> parameters, HttpRequest<?> request) {
        ProcessMeta.fromKogitoWorkItem(item).asMap().forEach(request::putHeader);
    }
}
