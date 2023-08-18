package org.kie.kogito.addons.quarkus.knative.serving.customfunctions;

import java.util.Map;

import org.kie.kogito.event.cloudevents.utils.CloudEventUtils;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kogito.workitem.rest.decorators.ParamsDecorator;

import io.vertx.mutiny.ext.web.client.HttpRequest;

import static org.kie.kogito.addons.quarkus.knative.serving.customfunctions.KnativeWorkItemHandler.APPLICATION_CLOUDEVENTS_JSON_CHARSET_UTF_8;
import static org.kie.kogito.addons.quarkus.knative.serving.customfunctions.KnativeWorkItemHandler.ID;

public final class CloudEventKnativeParamsDecorator implements ParamsDecorator {

    @Override
    public void decorate(KogitoWorkItem workItem, Map<String, Object> parameters, HttpRequest<?> request) {
        Map<String, Object> cloudEvent = KnativeFunctionPayloadSupplier.getPayload(parameters);

        if (cloudEvent.get(ID) == null) {
            String cloudEventId = generateCloudEventId(cloudEvent.hashCode(), workItem.getProcessInstanceStringId());
            cloudEvent.put(ID, cloudEventId);
            parameters.put(ID, cloudEventId);
        }

        CloudEventUtils.validateCloudEvent(cloudEvent);

        request.putHeader("Content-Type", APPLICATION_CLOUDEVENTS_JSON_CHARSET_UTF_8);
    }

    private static String generateCloudEventId(int uniqueIdentifier, String processInstanceId) {
        return processInstanceId + "_" + uniqueIdentifier;
    }
}
