package org.kie.kogito.serverless.workflow.executor;

import org.kie.kogito.serverless.workflow.python.PythonScriptWorkItemHandler;
import org.kie.kogito.serverless.workflow.python.PythonServiceWorkItemHandler;

public class StaticPythonScriptRegister implements StaticApplicationRegister {

    private PythonScriptWorkItemHandler scriptWIH;
    private PythonServiceWorkItemHandler svcWIH;

    @Override
    public void register(StaticWorkflowApplication application) {
        scriptWIH = new PythonScriptWorkItemHandler();
        application.registerHandler(scriptWIH);
        svcWIH = new PythonServiceWorkItemHandler();
        application.registerHandler(svcWIH);
    }

    @Override
    public void close() {
        if (scriptWIH != null) {
            scriptWIH.close();
        }
        if (svcWIH != null) {
            svcWIH.close();
        }
    }
}
