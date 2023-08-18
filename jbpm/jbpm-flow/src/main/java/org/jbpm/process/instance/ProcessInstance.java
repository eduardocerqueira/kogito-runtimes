package org.jbpm.process.instance;

import java.util.Date;
import java.util.Map;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.jbpm.workflow.instance.NodeInstance;
import org.kie.api.definition.process.Process;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

/**
 * A process instance is the representation of a process during its execution.
 * It contains all the runtime status information about the running process.
 * A process can have multiple instances.
 */
public interface ProcessInstance extends KogitoProcessInstance,
        ContextInstanceContainer,
        ContextableInstance {

    void setId(String id);

    Process getProcess();

    void setProcess(Process process);

    void setState(int state);

    void setState(int state, String outcome);

    void setState(int state, String outcome, Object faultData);

    void setErrorState(NodeInstance nodeInstanceInError, Exception e);

    InternalKnowledgeRuntime getKnowledgeRuntime();

    void setKnowledgeRuntime(InternalKnowledgeRuntime kruntime);

    void start();

    void start(String trigger);

    String getOutcome();

    void setParentProcessInstanceId(String parentId);

    void setRootProcessInstanceId(String parentId);

    void setRootProcessId(String processId);

    Map<String, Object> getMetaData();

    void setMetaData(String name, Object data);

    Object getFaultData();

    boolean isSignalCompletion();

    void setSignalCompletion(boolean signalCompletion);

    String getDeploymentId();

    void setDeploymentId(String deploymentId);

    Date getStartDate();

    void setStartDate(Date date);

    int getSlaCompliance();

    Date getSlaDueDate();

    void configureTimers();

    void setReferenceId(String referenceId);

    void disconnect();

    void reconnect();

    AgendaFilter getAgendaFilter();

    void setAgendaFilter(AgendaFilter agendaFilter);
}
