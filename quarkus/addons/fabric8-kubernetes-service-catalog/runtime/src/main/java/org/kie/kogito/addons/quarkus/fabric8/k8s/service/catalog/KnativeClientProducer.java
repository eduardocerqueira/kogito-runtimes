package org.kie.kogito.addons.quarkus.fabric8.k8s.service.catalog;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import io.fabric8.knative.client.KnativeClient;
import io.fabric8.kubernetes.client.KubernetesClient;

final class KnativeClientProducer {

    private final KubernetesClient kubernetesClient;

    @Inject
    KnativeClientProducer(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @Produces
    @ApplicationScoped
    KnativeClient createKnativeClient() {
        return kubernetesClient.adapt(KnativeClient.class);
    }
}
