package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.client.KubernetesClient;

abstract class AbstractResourceDiscovery {

    private static final Logger logger = LoggerFactory.getLogger(AbstractResourceDiscovery.class);

    protected final void logDefaultNamespace(String defaultNamespace) {
        logger.warn("Namespace is not set, setting namespace to the current context [{}].", defaultNamespace);
    }

    protected final void logConnection(KubernetesClient client, String resourceName) {
        logger.info("Connected to kubernetes cluster {}, current namespace is {}. Resource name for discovery is {}",
                client.getKubernetesVersion().getGitVersion(),
                client.getNamespace(),
                resourceName);
    }
}
