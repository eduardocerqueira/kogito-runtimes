package org.jbpm.workflow.instance.impl;

import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.integration.impl.SimpleValueResolver;

public class ProcessInstanceResolverFactory extends ImmutableDefaultFactory {

    private static final long serialVersionUID = 510l;

    private WorkflowProcessInstance processInstance;

    public ProcessInstanceResolverFactory(WorkflowProcessInstance processInstance) {
        this.processInstance = processInstance;
    }

    @Override
    public boolean isResolveable(String name) {
        return processInstance.getVariable(name) != null;
    }

    @Override
    public VariableResolver getVariableResolver(String name) {
        return new SimpleValueResolver(processInstance.getVariable(name));
    }

}
