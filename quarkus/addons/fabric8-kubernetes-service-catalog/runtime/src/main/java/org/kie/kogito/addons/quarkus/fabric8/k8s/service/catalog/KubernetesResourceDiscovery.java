package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import java.net.URI;
import java.util.Optional;
import java.util.function.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.client.KubernetesClient;

@ApplicationScoped
class KubernetesResourceDiscovery extends AbstractResourceDiscovery {

    private static final Logger logger = LoggerFactory.getLogger(KubernetesResourceDiscovery.class.getName());

    private final KubernetesClient kubernetesClient;

    private final KnativeServiceDiscovery knativeServiceDiscovery;

    @Inject
    KubernetesResourceDiscovery(KubernetesClient kubernetesClient, KnativeServiceDiscovery knativeServiceDiscovery) {
        this.kubernetesClient = kubernetesClient;
        this.knativeServiceDiscovery = knativeServiceDiscovery;
    }

    Optional<URI> query(KubernetesResourceUri resourceUri) {
        logConnection(kubernetesClient, resourceUri.getResourceName());

        resourceUri = resolveNamespace(resourceUri, kubernetesClient::getNamespace);

        switch (resourceUri.getGvk()) {
            case SERVICE:
                return ServiceUtils.queryServiceByName(kubernetesClient, resourceUri);

            case KNATIVE_SERVICE:
                return knativeServiceDiscovery.query(new KnativeServiceUri(resourceUri.getNamespace(), resourceUri.getResourceName()));

            case POD:
                return PodUtils.queryPodByName(kubernetesClient, resourceUri);

            case DEPLOYMENT:
                return DeploymentUtils.queryDeploymentByName(kubernetesClient, resourceUri);

            case STATEFUL_SET:
                return StatefulSetUtils.queryStatefulSetByName(kubernetesClient, resourceUri);

            case INGRESS:
                return IngressUtils.queryIngressByName(kubernetesClient, resourceUri);

            default:
                logger.debug("Resource kind {} is not supported yet.", resourceUri.getGvk().getValue());
                return Optional.empty();
        }
    }

    private KubernetesResourceUri resolveNamespace(KubernetesResourceUri uri, Supplier<String> defaultNamespaceSupplier) {
        if (uri.getNamespace() == null) {
            String defaultNamespace = defaultNamespaceSupplier.get();

            logDefaultNamespace(defaultNamespace);

            uri = uri.copyBuilder()
                    .withNamespace(defaultNamespace)
                    .build();
        }
        return uri;
    }
}
