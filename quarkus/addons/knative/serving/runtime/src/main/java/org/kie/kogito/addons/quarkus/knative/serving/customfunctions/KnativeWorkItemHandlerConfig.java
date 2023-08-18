package org.kie.kogito.addons.quarkus.knative.serving.customfunctions;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;

@ApplicationScoped
final class KnativeWorkItemHandlerConfig extends DefaultWorkItemHandlerConfig {

    @Inject
    KnativeWorkItemHandler handler;

    @PostConstruct
    void init() {
        register(handler.getName(), handler);
    }
}
