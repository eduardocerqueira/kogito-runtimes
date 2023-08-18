package org.kie.kogito.serverless.workflow.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.kie.kogito.internal.utils.ConversionUtils;

public class SystemPropertiesConfigResolver implements ConfigResolver {

    @Override
    public <T> Optional<T> getConfigProperty(String name, Class<T> clazz) {
        Object value = null;
        if (Integer.class.isAssignableFrom(clazz)) {
            value = Integer.getInteger(name);
        } else if (String.class.isAssignableFrom(clazz) || Object.class.equals(clazz)) {
            value = System.getProperty(name);
        }
        return Optional.ofNullable(clazz.cast(value));
    }

    @Override
    public Iterable getPropertyNames() {
        return System.getProperties().keySet();
    }

    @Override
    public Map asMap() {
        return System.getProperties();
    }

    @Override
    public <T> Collection<T> getIndexedConfigProperty(String name, Class<T> clazz) {
        return ConversionUtils.convertToCollection(System.getProperty(name), clazz);
    }
}
