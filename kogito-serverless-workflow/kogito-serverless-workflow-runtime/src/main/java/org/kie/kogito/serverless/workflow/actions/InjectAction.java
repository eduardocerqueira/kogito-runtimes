package org.kie.kogito.serverless.workflow.actions;

import org.jbpm.process.instance.impl.Action;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.jackson.utils.MergeUtils;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.serverless.workflow.SWFConstants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import static org.kie.kogito.serverless.workflow.actions.ActionUtils.getWorkflowData;

public class InjectAction implements Action {

    protected JsonNode node;

    public InjectAction(String json) {
        this(readObject(json));
    }

    public InjectAction(JsonNode node) {
        this.node = node;
    }

    private static JsonNode readObject(String json) {
        try {
            return ObjectMapperFactory.listenerAware().readTree(json);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        context.setVariable(SWFConstants.DEFAULT_WORKFLOW_VAR, MergeUtils.merge(node, getWorkflowData(context)));
    }
}
