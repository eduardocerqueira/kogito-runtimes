package org.kie.kogito.quarkus.serverless.workflow.python;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.process.impl.CachedWorkItemHandlerConfig;
import org.kie.kogito.serverless.workflow.python.PythonScriptWorkItemHandler;
import org.kie.kogito.serverless.workflow.python.PythonServiceWorkItemHandler;

@ApplicationScoped
public class PythonWorkItemHandlerConfig extends CachedWorkItemHandlerConfig {

    private PythonScriptWorkItemHandler scriptHandler;
    private PythonServiceWorkItemHandler serviceHandler;

    @PostConstruct
    void init() {
        scriptHandler = registerHandler(new PythonScriptWorkItemHandler());
        serviceHandler = registerHandler(new PythonServiceWorkItemHandler());
    }

    @PreDestroy
    void cleanup() {
        scriptHandler.close();
        serviceHandler.close();
    }

    private <T extends KogitoWorkItemHandler> T registerHandler(T handler) {
        register(handler.getName(), handler);
        return handler;
    }
}
