package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import java.net.URI;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.knative.client.KnativeClient;
import io.fabric8.knative.serving.v1.Service;

@ApplicationScoped
class KnativeServiceDiscovery extends AbstractResourceDiscovery {

    private static final Logger logger = LoggerFactory.getLogger(KnativeServiceDiscovery.class);

    private final KnativeClient knativeClient;

    @Inject
    KnativeServiceDiscovery(KnativeClient knativeClient) {
        this.knativeClient = knativeClient;
    }

    Optional<URI> query(KnativeServiceUri knativeServiceUri) {
        logConnection(knativeClient, knativeServiceUri.getResourceName());

        final String namespace;

        if (knativeServiceUri.getNamespace() == null) {
            namespace = knativeClient.getNamespace();
            logDefaultNamespace(namespace);
        } else {
            namespace = knativeServiceUri.getNamespace();
        }

        Service service = knativeClient.services().inNamespace(namespace).withName(knativeServiceUri.getResourceName()).get();
        if (null == service) {
            logger.error("Knative {} service not found on the {} namespace.", knativeServiceUri.getResourceName(), namespace);
            return Optional.empty();
        }
        logger.debug("Found Knative endpoint at {}", service.getStatus().getUrl());
        return Optional.of(URI.create(service.getStatus().getUrl()));
    }

    private void logConnection(KnativeClient client, String resourceName) {
        logger.info("Connected to Knative, current namespace is {}. Resource name for discovery is {}",
                client.getNamespace(),
                resourceName);
    }
}
