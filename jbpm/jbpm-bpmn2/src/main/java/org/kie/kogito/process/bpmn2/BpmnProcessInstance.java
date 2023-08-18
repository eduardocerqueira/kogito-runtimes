package org.kie.kogito.process.bpmn2;

import java.util.Collections;
import java.util.Map;

import org.jbpm.process.instance.InternalProcessRuntime;
import org.kie.api.runtime.process.WorkflowProcessInstance;
import org.kie.kogito.process.impl.AbstractProcess;
import org.kie.kogito.process.impl.AbstractProcessInstance;

public class BpmnProcessInstance extends AbstractProcessInstance<BpmnVariables> {

    public BpmnProcessInstance(AbstractProcess<BpmnVariables> process, BpmnVariables variables, InternalProcessRuntime rt) {
        super(process, variables, rt);
    }

    public BpmnProcessInstance(AbstractProcess<BpmnVariables> process, BpmnVariables variables, WorkflowProcessInstance wpi) {
        super(process, variables, wpi);
    }

    public BpmnProcessInstance(AbstractProcess<BpmnVariables> process, BpmnVariables variables, InternalProcessRuntime rt, WorkflowProcessInstance wpi) {
        super(process, variables, rt, wpi);
    }

    public BpmnProcessInstance(AbstractProcess<BpmnVariables> process, BpmnVariables variables, String businessKey, InternalProcessRuntime rt) {
        super(process, variables, businessKey, rt);
    }

    @Override
    protected Map<String, Object> bind(BpmnVariables variables) {
        if (variables == null) {
            return Collections.emptyMap();
        }
        return variables.toMap();
    }

    @Override
    protected void unbind(BpmnVariables variables, Map<String, Object> vmap) {
        if (variables == null || vmap == null) {
            return;
        }
        variables.fromMap(vmap);
    }
}
