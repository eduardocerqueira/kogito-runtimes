package org.kie.kogito.serverless.workflow.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;

import io.serverlessworkflow.api.functions.FunctionDefinition;

public class FunctionTypeHandlerFactory {

    public static FunctionTypeHandlerFactory instance() {
        return INSTANCE;
    }

    private static final FunctionTypeHandlerFactory INSTANCE = new FunctionTypeHandlerFactory();
    public static final String CUSTOM_TYPE_SEPARATOR = ":";

    // we need two maps to avoid clashing
    private final Map<String, FunctionTypeHandler> typeMap = new HashMap<>();
    private final Map<String, FunctionTypeHandler> customMap = new HashMap<>();

    public Optional<FunctionTypeHandler> getTypeHandler(FunctionDefinition functionDef) {
        final boolean isCustom = functionDef.getType() == FunctionDefinition.Type.CUSTOM;
        Map<String, FunctionTypeHandler> map;
        String key;
        if (isCustom) {
            map = customMap;
            key = getTypeFromOperation(functionDef);
        } else {
            map = typeMap;
            key = functionDef.getType().toString();
        }
        return Optional.ofNullable(map.get(key));
    }

    public static String getTypeFromOperation(FunctionDefinition functionDef) {
        String operation = functionDef.getOperation();
        int indexOf = operation.indexOf(CUSTOM_TYPE_SEPARATOR);
        return indexOf == -1 ? operation : operation.substring(0, indexOf);
    }

    public static String trimCustomOperation(FunctionDefinition functionDef) {
        String operation = functionDef.getOperation();
        int indexOf = operation.indexOf(CUSTOM_TYPE_SEPARATOR);
        return indexOf == -1 ? "" : operation.substring(indexOf + 1);
    }

    private FunctionTypeHandlerFactory() {
        ServiceLoader.load(FunctionTypeHandler.class).forEach(handler -> (handler.isCustom() ? customMap : typeMap).put(handler.type(), handler));
    }
}
