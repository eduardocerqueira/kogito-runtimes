package org.kie.kogito.serverless.workflow.python;

import java.util.Collections;
import java.util.Map;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.serverless.workflow.WorkflowWorkItemHandler;

import jep.Interpreter;

import static org.kie.kogito.serverless.workflow.SWFConstants.PYTHON_SCRIPT;
import static org.kie.kogito.serverless.workflow.SWFConstants.SCRIPT;
import static org.kie.kogito.serverless.workflow.python.PythonWorkItemHandlerUtils.closeInterpreter;
import static org.kie.kogito.serverless.workflow.python.PythonWorkItemHandlerUtils.interpreter;

public class PythonScriptWorkItemHandler extends WorkflowWorkItemHandler {

    @Override
    public String getName() {
        return PYTHON_SCRIPT;
    }

    @Override
    protected Object internalExecute(KogitoWorkItem workItem, Map<String, Object> parameters) {
        Interpreter py = interpreter();
        String source = (String) parameters.remove(SCRIPT);
        parameters.forEach(py::set);
        py.exec(source);
        return Collections.emptyMap();
    }

    public void close() {
        closeInterpreter();
    }

}
