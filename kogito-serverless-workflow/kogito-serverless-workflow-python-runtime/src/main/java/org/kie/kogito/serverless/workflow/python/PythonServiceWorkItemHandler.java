package org.kie.kogito.serverless.workflow.python;

import java.util.Map;

import org.kie.kogito.jackson.utils.JsonObjectUtils;
import org.kie.kogito.serverless.workflow.ServiceWorkItemHandler;

import com.fasterxml.jackson.databind.JsonNode;

import jep.Interpreter;

import static org.kie.kogito.serverless.workflow.SWFConstants.PYTHON_SVC;
import static org.kie.kogito.serverless.workflow.python.PythonWorkItemHandlerUtils.closeInterpreter;
import static org.kie.kogito.serverless.workflow.python.PythonWorkItemHandlerUtils.interpreter;

public class PythonServiceWorkItemHandler extends ServiceWorkItemHandler {

    @Override
    public String getName() {
        return PYTHON_SVC;
    }

    public void close() {
        closeInterpreter();
    }

    @Override
    protected Object invoke(String moduleName, String methodName,
            Object parameters) {
        Interpreter py = interpreter();
        // make sure module is imported
        py.exec("import " + moduleName);
        final String funcName = moduleName + "." + methodName;
        if (parameters instanceof Map) {
            return py.invoke(funcName, (Map<String, Object>) parameters);
        }
        if (parameters instanceof JsonNode) {
            return py.invoke(funcName, JsonObjectUtils.convertValue((JsonNode) parameters, Map.class));
        }
        return py.invoke(funcName, parameters);
    }
}
