package org.kie.kogito.serverless.workflow.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.kie.kogito.internal.utils.ConversionUtils;

public class MapConfigResolver implements ConfigResolver {

    private final Map<String, Object> map;

    public MapConfigResolver(Map<String, Object> map) {
        this.map = map;
    }

    public MapConfigResolver(Properties props) {
        this((Map) props);
    }

    @Override
    public <T> Optional<T> getConfigProperty(String name, Class<T> clazz) {
        return Optional.ofNullable(clazz.cast(map.get(name)));
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return map.keySet();
    }

    @Override
    public Map<String, Object> asMap() {
        return map;
    }

    @Override
    public <T> Collection<T> getIndexedConfigProperty(String name, Class<T> clazz) {
        return ConversionUtils.convertToCollection(map.get(name), clazz);
    }
}
