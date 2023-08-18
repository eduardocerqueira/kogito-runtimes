package org.kie.kogito.serverless.workflow.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface ConfigResolver {

    <T> Optional<T> getConfigProperty(String name, Class<T> clazz);

    Iterable<String> getPropertyNames();

    <T> Collection<T> getIndexedConfigProperty(String name, Class<T> clazz);

    default Map<String, Object> asMap() {
        Map<String, Object> map = new HashMap<>();
        for (String name : getPropertyNames()) {
            map.put(name, getConfigProperty(name, Object.class).orElseThrow());
        }
        return map;
    }
}
