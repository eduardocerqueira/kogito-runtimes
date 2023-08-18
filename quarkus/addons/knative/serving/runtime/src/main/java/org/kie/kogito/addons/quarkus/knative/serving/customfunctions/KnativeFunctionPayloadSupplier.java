package org.kie.kogito.addons.quarkus.knative.serving.customfunctions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static org.kie.kogito.addons.quarkus.knative.serving.customfunctions.KnativeWorkItemHandler.PAYLOAD_FIELDS_DELIMITER;
import static org.kie.kogito.addons.quarkus.knative.serving.customfunctions.KnativeWorkItemHandler.PAYLOAD_FIELDS_PROPERTY_NAME;

final class KnativeFunctionPayloadSupplier {

    private KnativeFunctionPayloadSupplier() {
    }

    static Map<String, Object> getPayload(Map<String, Object> parameters) {
        return getPayloadFields(parameters).stream()
                .collect(toMap(Function.identity(), parameters::get));
    }

    private static List<String> getPayloadFields(Map<String, Object> parameters) {
        String payloadFields = (String) parameters.remove(PAYLOAD_FIELDS_PROPERTY_NAME);
        if (payloadFields != null) {
            return Arrays.asList(payloadFields.split(PAYLOAD_FIELDS_DELIMITER));
        } else {
            return List.of();
        }
    }
}
