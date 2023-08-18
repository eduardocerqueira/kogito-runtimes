package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import java.util.Locale;
import java.util.Optional;

enum GVK {

    DEPLOYMENT("deployments.v1.apps"),
    DEPLOYMENT_CONFIG("deploymentconfigs.v1.apps.openshift.io"),
    STATEFUL_SET("statefulsets.v1.apps"),
    SERVICE("services.v1"),
    ROUTE("routes.v1.route.openshift.io"),
    INGRESS("ingresses.v1.networking.k8s.io"),
    POD("pods.v1"),
    KNATIVE_SERVICE("services.v1.serving.knative.dev");

    private final String value;

    GVK(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }

    static GVK from(String version, String kind) {
        return from(kind + "." + version);
    }

    static GVK from(String group, String version, String kind) {
        return from(kind + "." + version + "." + group);
    }

    private static String sanitize(String value) {
        return value.toLowerCase(Locale.ROOT);
    }

    static GVK from(String value) {
        return fromInternal(value).orElseThrow(() -> new IllegalArgumentException("Given GVK is not valid or supported: " + value));
    }

    private static Optional<GVK> fromInternal(String value) {
        String sanitizedValue = sanitize(value);

        for (GVK gvk : GVK.values()) {
            if (gvk.value.equals(sanitizedValue)) {
                return Optional.of(gvk);
            }
        }

        return Optional.empty();
    }

    static boolean isValid(String value) {
        return fromInternal(value).isPresent();
    }
}
