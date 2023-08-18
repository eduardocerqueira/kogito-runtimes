package org.jbpm.process.core.datatype.impl.coverter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import com.fasterxml.jackson.databind.JsonNode;

public class CloneHelperRegister {

    private static final CloneHelperRegister instance = new CloneHelperRegister();

    public static CloneHelperRegister get() {
        return instance;
    }

    private Map<Class<?>, UnaryOperator<?>> registeredCloners = new HashMap<>();

    private CloneHelperRegister() {
        registeredCloners.put(JsonNode.class, o -> ((JsonNode) o).deepCopy());
    }

    public <T> void registerCloner(Class<T> type, UnaryOperator<T> cloner) {
        registeredCloners.put(type, cloner);
    }

    public Map<Class<?>, UnaryOperator<?>> getCloners() {
        return Collections.unmodifiableMap(registeredCloners);
    }
}
