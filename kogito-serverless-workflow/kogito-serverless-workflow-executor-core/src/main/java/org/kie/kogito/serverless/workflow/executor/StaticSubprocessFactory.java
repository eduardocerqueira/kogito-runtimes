package org.kie.kogito.serverless.workflow.executor;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.workflow.core.impl.NodeIoHelper;
import org.jbpm.workflow.core.node.SubProcessFactory;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.serverless.workflow.SWFConstants;
import org.kie.kogito.serverless.workflow.models.JsonNodeModel;

class StaticSubprocessFactory implements SubProcessFactory<JsonNodeModel> {

    private final Process<JsonNodeModel> subprocess;

    public StaticSubprocessFactory(Process<JsonNodeModel> subprocess) {
        this.subprocess = subprocess;
    }

    public JsonNodeModel bind(org.kie.api.runtime.process.ProcessContext kcontext) {
        JsonNodeModel model = new JsonNodeModel();
        model.update(NodeIoHelper.processInputs((NodeInstanceImpl) kcontext.getNodeInstance(), kcontext::getVariable));
        return model;
    }

    public ProcessInstance<JsonNodeModel> createInstance(JsonNodeModel model) {
        return subprocess.createInstance(model);
    }

    public void unbind(org.kie.api.runtime.process.ProcessContext kcontext, JsonNodeModel model) {
        Map<String, Object> outputs = new HashMap<>();
        outputs.put(SWFConstants.DEFAULT_WORKFLOW_VAR, model.getWorkflowdata());
        NodeIoHelper.processOutputs((NodeInstanceImpl) kcontext.getNodeInstance(), outputs::get, kcontext::getVariable);
    }
}
