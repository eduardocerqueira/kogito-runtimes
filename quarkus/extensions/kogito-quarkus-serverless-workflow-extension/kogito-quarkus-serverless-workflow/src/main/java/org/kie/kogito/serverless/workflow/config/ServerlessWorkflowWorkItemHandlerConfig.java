package org.kie.kogito.serverless.workflow.config;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;
import org.kie.kogito.serverless.workflow.WorkflowWorkItemHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ServerlessWorkflowWorkItemHandlerConfig extends DefaultWorkItemHandlerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerlessWorkflowWorkItemHandlerConfig.class);

    @Inject
    Instance<WorkflowWorkItemHandler> handlers;

    @PostConstruct
    public void init() {
        handlers.forEach(handler -> {
            LOGGER.info("Registering OpenAPI work item handler named: {}", handler.getName());
            register(handler.getName(), handler);
        });
    }

}
