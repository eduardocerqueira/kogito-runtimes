package org.kie.kogito.addons.quarkus.knative.serving.customfunctions;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.kie.kogito.addons.k8s.resource.catalog.KubernetesServiceCatalog;
import org.kogito.workitem.rest.RestWorkItemHandlerUtils;

import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

public final class KnativeWorkItemHandlerProducer {

    @Inject
    Vertx vertx;

    @Inject
    KubernetesServiceCatalog kubernetesServiceCatalog;

    @Produces
    KnativeWorkItemHandler createKnativeWorkItemHandler() {
        return new KnativeWorkItemHandler(getHttpClient(), getHttpsClient(), kubernetesServiceCatalog);
    }

    private WebClient getHttpsClient() {
        return WebClient.create(vertx, RestWorkItemHandlerUtils.sslWebClientOptions());
    }

    private WebClient getHttpClient() {
        return WebClient.create(vertx, new WebClientOptions());
    }
}
