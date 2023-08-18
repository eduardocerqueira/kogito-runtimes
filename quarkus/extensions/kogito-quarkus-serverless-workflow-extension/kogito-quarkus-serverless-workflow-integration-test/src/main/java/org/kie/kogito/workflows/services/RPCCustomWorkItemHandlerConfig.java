package org.kie.kogito.workflows.services;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kie.kogito.process.impl.CachedWorkItemHandlerConfig;

@ApplicationScoped
public class RPCCustomWorkItemHandlerConfig extends CachedWorkItemHandlerConfig {

    @Inject
    RPCCustomWorkItemHandler handler;

    @PostConstruct
    void init() {
        register(handler.getName(), handler);
    }
}
