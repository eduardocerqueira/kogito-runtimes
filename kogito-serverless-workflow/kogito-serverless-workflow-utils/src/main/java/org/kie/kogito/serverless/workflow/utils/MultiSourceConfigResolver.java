package org.kie.kogito.serverless.workflow.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.kie.kogito.internal.utils.ConversionUtils;

public class MultiSourceConfigResolver implements ConfigResolver {

    private final Iterable<ConfigResolver> configResolvers;

    private AtomicReference<Map<String, Object>> map = new AtomicReference<>();

    public static MultiSourceConfigResolver of(Collection<ConfigResolver> resolvers) {
        return new MultiSourceConfigResolver(resolvers);
    }

    public static MultiSourceConfigResolver withSystemProperties(Map<String, Object> props) {
        return new MultiSourceConfigResolver(Arrays.asList(new MapConfigResolver(props), new SystemPropertiesConfigResolver()));
    }

    private MultiSourceConfigResolver(Iterable<ConfigResolver> resolvers) {
        this.configResolvers = resolvers;
    }

    @Override
    public <T> Optional<T> getConfigProperty(String name, Class<T> clazz) {
        Map<String, Object> collect = map.get();
        if (collect != null) {
            return Optional.ofNullable(clazz.cast(collect.get(name)));
        }
        for (ConfigResolver resolver : configResolvers) {
            Optional<T> value = resolver.getConfigProperty(name, clazz);
            if (value.isPresent()) {
                return value;
            }
        }
        return Optional.empty();
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> collect = map.get();
        if (collect == null) {
            collect = new HashMap<>();
            for (ConfigResolver resolver : configResolvers) {
                collect.putAll(resolver.asMap());
            }
            map.set(collect);
        }
        return collect;
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return asMap().keySet();
    }

    @Override
    public <T> Collection<T> getIndexedConfigProperty(String name, Class<T> clazz) {
        Map<String, Object> collect = map.get();
        if (collect != null) {
            return ConversionUtils.convertToCollection(collect.get(name), clazz);
        }
        Collection<T> result = new LinkedHashSet<>();
        for (ConfigResolver resolver : configResolvers) {
            result.addAll(resolver.getIndexedConfigProperty(name, clazz));
        }
        return result;
    }
}
