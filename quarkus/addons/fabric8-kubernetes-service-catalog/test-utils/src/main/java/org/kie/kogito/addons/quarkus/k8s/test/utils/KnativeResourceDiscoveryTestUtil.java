package org.kie.kogito.addons.quarkus.k8s.test.utils;

import java.io.InputStream;

import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.serving.v1.Service;
import io.fabric8.kubernetes.client.server.mock.KubernetesServer;

public final class KnativeResourceDiscoveryTestUtil {

    private KnativeResourceDiscoveryTestUtil() {
    }

    public static void createServiceIfNotExists(KubernetesServer k8sServer, String knativeYaml, String namespace, String serviceName) {
        createServiceIfNotExists(k8sServer, knativeYaml, namespace, serviceName, null);
    }

    @SuppressWarnings("deprecation") // Quarkus LTS 2.13 compatibility
    public static void createServiceIfNotExists(KubernetesServer k8sServer, String knativeYaml, String namespace, String serviceName, String remoteServiceUrl) {
        if (k8sServer.getClient().services().inNamespace(namespace).withName(serviceName).get() == null) {
            KnativeClient knativeClient = k8sServer.getClient().adapt(KnativeClient.class);

            Service service = knativeClient.services()
                    .inNamespace(namespace)
                    .load(getResourceAsStream(knativeYaml))
                    .get();

            if (remoteServiceUrl != null) {
                service.getStatus().setUrl(remoteServiceUrl);
            }

            // ItemWritableOperation#create is deprecated. However, we can't use the new method while Quarkus LTS is not greater than 2.16.
            knativeClient.services().inNamespace(namespace).create(service);
        }
    }

    private static InputStream getResourceAsStream(String knativeYaml) {
        return KnativeResourceDiscoveryTestUtil.class.getClassLoader().getResourceAsStream(knativeYaml);
    }
}
