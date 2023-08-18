package org.jbpm.process.instance;

import java.util.Map;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.jbpm.process.core.ContextContainer;
import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.process.instance.context.variable.VariableScopeInstance;
import org.kie.api.definition.process.Process;
import org.kie.internal.process.CorrelationKey;

public abstract class AbstractProcessInstanceFactory implements ProcessInstanceFactory {

    public ProcessInstance createProcessInstance(Process process, CorrelationKey correlationKey,
            InternalKnowledgeRuntime kruntime,
            Map<String, Object> parameters) {
        ProcessInstance processInstance = createProcessInstance();
        processInstance.setKnowledgeRuntime(kruntime);
        processInstance.setProcess(process);

        if (correlationKey != null) {
            processInstance.getMetaData().put("CorrelationKey", correlationKey);
        }

        ((InternalProcessRuntime) kruntime.getProcessRuntime()).getProcessInstanceManager().addProcessInstance(processInstance);

        // set variable default values
        // TODO: should be part of processInstanceImpl?
        VariableScope variableScope = (VariableScope) ((ContextContainer) process).getDefaultContext(VariableScope.VARIABLE_SCOPE);
        VariableScopeInstance variableScopeInstance = (VariableScopeInstance) processInstance.getContextInstance(VariableScope.VARIABLE_SCOPE);
        // set input parameters
        if (parameters != null) {
            if (variableScope != null) {
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {

                    variableScope.validateVariable(process.getName(), entry.getKey(), entry.getValue());
                    variableScopeInstance.setVariable(entry.getKey(), entry.getValue());
                }
            } else {
                throw new IllegalArgumentException("This process does not support parameters!");
            }
        }
        variableScopeInstance.enforceRequiredVariables();

        return processInstance;
    }

    public abstract ProcessInstance createProcessInstance();

}
