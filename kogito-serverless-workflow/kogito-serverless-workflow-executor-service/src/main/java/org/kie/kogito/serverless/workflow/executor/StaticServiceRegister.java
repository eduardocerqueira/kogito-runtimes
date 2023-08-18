package org.kie.kogito.serverless.workflow.executor;

public class StaticServiceRegister implements StaticApplicationRegister {

    @Override
    public void register(StaticWorkflowApplication application) {
        application.registerHandler(new StaticServiceWorkItemHandler());
    }
}
