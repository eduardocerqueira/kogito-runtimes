package org.jbpm.process.instance.command;

public interface KogitoProcessInstanceIdCommand {

    void setProcessInstanceId(String procInstId);

    String getProcessInstanceId();
}
