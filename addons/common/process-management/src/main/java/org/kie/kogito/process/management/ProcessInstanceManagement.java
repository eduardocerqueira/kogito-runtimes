package org.kie.kogito.process.management;

public interface ProcessInstanceManagement<T> {

    T getProcesses();

    T getProcessInfo(String processId);

    T getProcessNodes(String processId);

    T getInstanceInError(String processId, String processInstanceId);

    T getWorkItemsInProcessInstance(String processId, String processInstanceId);

    T retriggerInstanceInError(String processId, String processInstanceId);

    T skipInstanceInError(String processId, String processInstanceId);

    T triggerNodeInstanceId(String processId, String processInstanceId, String nodeId);

    T retriggerNodeInstanceId(String processId, String processInstanceId, String nodeInstanceId);

    T cancelNodeInstanceId(String processId, String processInstanceId, String nodeInstanceId);

    T cancelProcessInstanceId(String processId, String processInstanceId);
}
