package org.kie.kogito.serverless.workflow.functions;

import java.util.function.Function;

import io.serverlessworkflow.api.functions.FunctionDefinition;

public class FunctionDefinitionEx<T, V> extends FunctionDefinition {

    private static final long serialVersionUID = 1L;
    private transient Function<T, V> function;

    public FunctionDefinitionEx(String name) {
        super(name);
    }

    public FunctionDefinition withFunction(Function<T, V> function) {
        this.function = function;
        return this;
    }

    public Function<T, V> getFunction() {
        return function;
    }
}
