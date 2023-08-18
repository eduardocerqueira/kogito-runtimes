package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.client.KubernetesClient;

class StatefulSetUtils {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    private StatefulSetUtils() {
    }

    /**
     * Try to resolve the valid endpoint for the given StatefulSet, it first tries to get attached services, if no service
     * is found then try the PodIP from the given Deployment
     *
     * @param client
     * @param kubeURI
     * @return the valid endpoint for the given StatefulSet
     */
    static Optional<URI> queryStatefulSetByName(KubernetesClient client, KubernetesResourceUri kubeURI) {

        StatefulSet statefulSet = client.apps().statefulSets()
                .inNamespace(kubeURI.getNamespace())
                .withName(kubeURI.getResourceName())
                .get();

        if (statefulSet == null) {
            logger.error("StatefulSet {} not found on the {} namespace.", kubeURI.getResourceName(), kubeURI.getNamespace());
            return Optional.empty();
        }

        return ServiceUtils.queryServiceByLabelOrSelector(
                client,
                statefulSet.getSpec().getTemplate().getMetadata().getLabels(),
                statefulSet.getSpec().getSelector().getMatchLabels(),
                kubeURI)
                .or(() -> {
                    if (statefulSet.getStatus().getReplicas() == 1) {
                        logger.debug("searching for statefulSet [{}] child pod with UID [{}]",
                                kubeURI.getResourceName(),
                                statefulSet.getMetadata().getUid());

                        return PodUtils.queryPodByOwnerReference(
                                client,
                                statefulSet.getMetadata().getUid(),
                                kubeURI.getNamespace(),
                                kubeURI);
                    } else if (statefulSet.getStatus().getReplicas() > 1) {
                        logger.warn("Stateful set has {} replicas but no service was found, KubeURI {} will not be translated.",
                                statefulSet.getSpec().getReplicas(),
                                kubeURI);
                    }
                    return Optional.empty();
                });
    }
}
