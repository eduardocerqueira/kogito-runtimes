package org.kie.kogito.serverless.workflow.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

import io.serverlessworkflow.api.functions.FunctionRef;

public class FunctionNamespaceFactory {

    public static FunctionNamespaceFactory instance() {
        return INSTANCE;
    }

    private static final FunctionNamespaceFactory INSTANCE = new FunctionNamespaceFactory();
    private static final String NAMESPACE_SEPARATOR = ":";

    private final Map<String, FunctionNamespace> namespaceMap = new HashMap<>();

    public Optional<FunctionNamespace> getNamespace(FunctionRef functionDef) {
        return Optional.ofNullable(namespaceMap.get(extractNamespace(functionDef)));
    }

    private static String extractNamespace(FunctionRef functionRef) {
        String name = functionRef.getRefName();
        int indexOf = name.lastIndexOf(NAMESPACE_SEPARATOR);
        return indexOf == -1 ? name : name.substring(0, indexOf);
    }

    public static String getFunctionName(FunctionRef functionRef) {
        String name = functionRef.getRefName();
        int indexOf = name.lastIndexOf(NAMESPACE_SEPARATOR);
        return indexOf == -1 ? name : name.substring(indexOf + 1);
    }

    private FunctionNamespaceFactory() {
        ServiceLoader.load(FunctionNamespace.class).forEach(handler -> namespaceMap.put(handler.namespace(), handler));
    }
}
