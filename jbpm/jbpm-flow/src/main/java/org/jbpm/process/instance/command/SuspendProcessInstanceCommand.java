package org.jbpm.process.instance.command;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.kie.api.command.ExecutableCommand;
import org.kie.api.runtime.Context;
import org.kie.api.runtime.KieSession;
import org.kie.internal.command.RegistryContext;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

@XmlRootElement(name = "suspend-process-instance-command")
@XmlAccessorType(XmlAccessType.NONE)
public class SuspendProcessInstanceCommand implements ExecutableCommand<Object>, KogitoProcessInstanceIdCommand {

    /** Generated serial version UID */
    private static final long serialVersionUID = 5824052805419980114L;

    @XmlAttribute
    @XmlSchemaType(name = "string")
    private String processInstanceId;

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    @Override
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Object execute(Context context) {
        KogitoProcessRuntime runtime = (KogitoProcessRuntime) ((RegistryContext) context).lookup(KieSession.class);
        if (processInstanceId == null) {
            return null;
        }
        KogitoProcessInstance processInstance = runtime.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            throw new IllegalArgumentException("Could not find process instance for id " + processInstanceId);
        }
        if (processInstance.getState() != KogitoProcessInstance.STATE_ACTIVE) {
            throw new IllegalArgumentException("Process instance with id " + processInstanceId + " in state " + processInstance.getState());
        }
        ((org.jbpm.process.instance.ProcessInstance) processInstance).setState(KogitoProcessInstance.STATE_SUSPENDED);
        return null;
    }

    public String toString() {
        return "session.abortProcessInstance(" + processInstanceId + ");";
    }

}
