package org.kie.kogito.addons.quarkus.knative.serving.customfunctions;

import java.util.List;
import java.util.Map;

import org.kie.kogito.event.cloudevents.utils.CloudEventUtils;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kogito.workitem.rest.decorators.ParamsDecorator;

import io.vertx.mutiny.ext.web.client.HttpRequest;

import static org.kie.kogito.addons.quarkus.knative.serving.customfunctions.KnativeWorkItemHandler.CLOUDEVENT_SENT_AS_PLAIN_JSON_ERROR_MESSAGE;
import static org.kie.kogito.addons.quarkus.knative.serving.customfunctions.KnativeWorkItemHandler.ID;

public final class PlainJsonKnativeParamsDecorator implements ParamsDecorator {

    @Override
    public void decorate(KogitoWorkItem workItem, Map<String, Object> parameters, HttpRequest<?> request) {
        if (isCloudEvent(KnativeFunctionPayloadSupplier.getPayload(parameters))) {
            throw new IllegalArgumentException(CLOUDEVENT_SENT_AS_PLAIN_JSON_ERROR_MESSAGE);
        }
    }

    private static boolean isCloudEvent(Map<String, Object> payload) {
        List<String> cloudEventMissingAttributes = CloudEventUtils.getMissingAttributes(payload);
        return !payload.isEmpty() && (cloudEventMissingAttributes.isEmpty() || (cloudEventMissingAttributes.size() == 1 && cloudEventMissingAttributes.contains(ID)));
    }
}
