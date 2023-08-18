package org.kie.kogito.serverless.workflow.python;

import java.util.Collection;

import org.kie.kogito.serverless.workflow.utils.ConfigResolverHolder;

import jep.Interpreter;
import jep.SharedInterpreter;

public class PythonWorkItemHandlerUtils {

    private PythonWorkItemHandlerUtils() {
    }

    public static final String SEARCH_PATH_PROPERTY = "org.sonataflow.python.searchpath";
    private static final String PYTHON_SYS_PATH = "sys.path.append('%s')\n";

    private static final ThreadLocal<Interpreter> interpreter = new ThreadLocal<>();

    protected static Interpreter interpreter() {
        Interpreter py = interpreter.get();
        if (py == null) {
            py = new SharedInterpreter();
            interpreter.set(py);
            Collection<String> searchPath = ConfigResolverHolder.getConfigResolver().getIndexedConfigProperty(SEARCH_PATH_PROPERTY, String.class);
            if (!searchPath.isEmpty()) {
                StringBuilder sb = new StringBuilder("import sys\n");
                searchPath.forEach(path -> sb.append(String.format(PYTHON_SYS_PATH, path)));
                py.exec(sb.toString());
            }
        }
        return py;
    }

    protected static void closeInterpreter() {
        Interpreter py = interpreter.get();
        if (py != null) {
            interpreter.remove();
            py.close();
        }
    }

    protected static Object getValue(String key) {
        return interpreter().getValue(key);
    }
}
