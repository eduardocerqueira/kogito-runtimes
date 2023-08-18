package org.kie.kogito.addons.quarkus.knative.eventing.deployment;

import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;

public final class KnativeResourcesUtil {

    private static final String SINK_BINDING_SUFFIX = "sb";
    private static final String TRIGGER_SUFFIX = "trigger";

    private KnativeResourcesUtil() {
    }

    public static String generateTriggerName(final String ceType, final String appName) {
        final String triggerName = String.format("%s-%s-%s", ceType, TRIGGER_SUFFIX, appName);
        return KubernetesResourceUtil.sanitizeName(triggerName);
    }

    public static String generateSinkBindingName(final String appName) {
        final String sinkBindingName = String.format("%s-%s", SINK_BINDING_SUFFIX, appName);
        return KubernetesResourceUtil.sanitizeName(sinkBindingName);
    }
}
