package org.jbpm.process.instance.command;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.jbpm.process.instance.impl.ProcessInstanceImpl;
import org.kie.api.command.ExecutableCommand;
import org.kie.api.runtime.Context;
import org.kie.api.runtime.KieSession;
import org.kie.internal.command.RegistryContext;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

@XmlRootElement(name = "update-process-command")
@XmlAccessorType(XmlAccessType.NONE)
public class UpdateProcessCommand implements ExecutableCommand<Void>, KogitoProcessInstanceIdCommand {

    private static final long serialVersionUID = 6L;

    @XmlElement
    @XmlSchemaType(name = "string")
    private String processInstanceId;

    @XmlElement
    @XmlSchemaType(name = "string")
    private String processXml;

    public UpdateProcessCommand(String processInstanceId, String processXml) {
        this.processInstanceId = processInstanceId;
        this.processXml = processXml;
    }

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    @Override
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessXml() {
        return processXml;
    }

    public void setProcessXml(String processXml) {
        this.processXml = processXml;
    }

    public Void execute(Context context) {
        KogitoProcessRuntime runtime = (KogitoProcessRuntime) ((RegistryContext) context).lookup(KieSession.class);
        ProcessInstanceImpl processInstance = (ProcessInstanceImpl) runtime.getProcessInstance(processInstanceId);
        if (processInstance != null) {
            processInstance.setProcessXml(processXml);
        }
        return null;
    }

    public String toString() {
        return "((ProcessInstanceImpl) ksession.getProcessInstance("
                + processInstanceId + ")).setProcessXml(" + processXml + ");";
    }
}
