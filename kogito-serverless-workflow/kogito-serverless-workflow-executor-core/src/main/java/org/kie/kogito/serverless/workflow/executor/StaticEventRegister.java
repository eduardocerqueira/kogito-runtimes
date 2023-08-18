package org.kie.kogito.serverless.workflow.executor;

import org.jbpm.process.core.event.StaticMessageConsumer;
import org.jbpm.workflow.core.node.EventNode;
import org.kie.kogito.event.impl.EventFactoryUtils;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcess;

import com.fasterxml.jackson.databind.JsonNode;

import io.serverlessworkflow.api.Workflow;

import static org.jbpm.ruleflow.core.Metadata.EVENT_TYPE;
import static org.jbpm.ruleflow.core.Metadata.EVENT_TYPE_MESSAGE;
import static org.jbpm.ruleflow.core.Metadata.TRIGGER_REF;

public class StaticEventRegister implements StaticProcessRegister {

    @Override
    public void register(StaticWorkflowApplication application, Workflow workflow, StaticWorkflowProcess process) {
        ((KogitoWorkflowProcess) process.get()).getNodesRecursively().stream().filter(EventNode.class::isInstance).map(EventNode.class::cast)
                .filter(node -> EVENT_TYPE_MESSAGE.equals(node.getMetaData(EVENT_TYPE)))
                .forEach(node -> StaticMessageConsumer.of(application, process, JsonNode.class, (String) node.getMetaData(TRIGGER_REF)).build());
        application.registerCloseable(EventFactoryUtils::cleanUp);
    }
}
