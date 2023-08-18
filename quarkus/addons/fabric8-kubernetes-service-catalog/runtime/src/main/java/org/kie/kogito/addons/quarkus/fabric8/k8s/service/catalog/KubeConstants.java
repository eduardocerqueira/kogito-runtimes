package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

final class KubeConstants {

    private KubeConstants() {
    }

    // k8s service types
    static final String EXTERNAL_NAME_TYPE = "ExternalName";
    static final String NODE_PORT_TYPE = "NodePort";
    static final String CLUSTER_IP_TYPE = "ClusterIP";
    static final String LOAD_BALANCER_TYPE = "LoadBalancer";

    // Networking
    static final String SECURE_HTTP_PROTOCOL = "https";
    static final String NONSECURE_HTTP_PROTOCOL = "http";
    static final String WEB_PORT_NAME = "web";

    static final int NON_SECURE_PORT = 80;
    static final int SECURE_PORT = 443;
    static final int APP_SECURE_PORT = 8443;

    // Custom Query params
    static final String CUSTOM_PORT_NAME_PROPERTY = "port-name";
    static final String CUSTOM_RESOURCE_LABEL_PROPERTY = "labels";
}
