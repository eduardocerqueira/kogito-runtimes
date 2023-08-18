package org.jbpm.workflow.instance.impl;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.process.instance.context.variable.VariableScopeInstance;
import org.jbpm.workflow.instance.NodeInstance;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.integration.impl.SimpleValueResolver;

public class NodeInstanceResolverFactory extends ImmutableDefaultFactory {

    private static final long serialVersionUID = 510l;

    private NodeInstance nodeInstance;

    private Map<String, Object> extraParameters = new HashMap<>();

    public NodeInstanceResolverFactory(NodeInstance nodeInstance) {
        this.nodeInstance = nodeInstance;
        this.extraParameters.put("nodeInstance", nodeInstance);
        if (nodeInstance.getProcessInstance() != null) {
            this.extraParameters.put("processInstance", nodeInstance.getProcessInstance());
            this.extraParameters.put("processInstanceId", nodeInstance.getKogitoProcessInstance().getStringId());
            this.extraParameters.put("parentProcessInstanceId", nodeInstance.getKogitoProcessInstance().getParentProcessInstanceId());
        }
    }

    @Override
    public boolean isResolveable(String name) {
        boolean found = nodeInstance.resolveContextInstance(VariableScope.VARIABLE_SCOPE, name) != null;
        if (!found) {
            return extraParameters.containsKey(name);
        }

        return found;
    }

    @Override
    public VariableResolver getVariableResolver(String name) {
        if (extraParameters.containsKey(name)) {
            return new SimpleValueResolver(extraParameters.get(name));
        }

        Object value = ((VariableScopeInstance) nodeInstance.resolveContextInstance(
                VariableScope.VARIABLE_SCOPE, name)).getVariable(name);
        return new SimpleValueResolver(value);
    }

    public void addExtraParameters(Map<String, Object> parameters) {
        this.extraParameters.putAll(parameters);
    }
}
