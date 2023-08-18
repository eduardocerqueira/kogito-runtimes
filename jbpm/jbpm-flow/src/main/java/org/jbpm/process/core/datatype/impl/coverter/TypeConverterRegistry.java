package org.jbpm.process.core.datatype.impl.coverter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.jbpm.process.core.context.variable.Variable;
import org.jbpm.process.core.datatype.DataType;
import org.kie.kogito.jackson.utils.JsonNodeConverter;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.jackson.utils.StringConverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class TypeConverterRegistry {

    private static TypeConverterRegistry INSTANCE = new TypeConverterRegistry();

    private Map<String, Function<String, ? extends Object>> converters = new HashMap<>();
    private Map<String, Function<? extends Object, String>> unconverters = new HashMap<>();
    private Function<String, String> defaultConverter = new NoOpTypeConverter();

    private TypeConverterRegistry() {
        converters.put(Date.class.getName(), new DateTypeConverter());
        converters.put(JsonNode.class.getName(), new JsonNodeConverter(ObjectMapperFactory::listenerAware));
        unconverters.put(JsonNode.class.getName(), new StringConverter());
        addJacksonPair(Variable.class, Map.class);
    }

    private void addJacksonPair(Class<?>... classes) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(DataType.class, new DataTypeSerializer()).addDeserializer(DataType.class, new DataTypeDeserializer());
        ObjectMapperFactory.get().registerModule(module);
        for (Class<?> clazz : classes) {
            converters.put(clazz.getName(), new JacksonConverter<>(clazz));
            unconverters.put(clazz.getName(), new JacksonUnconverter<>());
        }
    }

    public boolean isRegistered(String type) {
        return converters.containsKey(type);
    }

    public Function<String, ? extends Object> forType(String type) {
        return converters.getOrDefault(type, defaultConverter);
    }

    public <T> Function<T, String> forTypeReverse(T obj) {
        Function<T, String> result = null;
        Class<?> clazz = obj.getClass();
        do {
            result = (Function<T, String>) unconverters.get(clazz.getName());
            clazz = clazz.getSuperclass();
        } while (clazz != null && result == null);
        return result == null ? Object::toString : result;
    }

    public TypeConverterRegistry register(String type, Function<String, ? extends Object> converter) {
        converters.put(type, converter);
        return this;
    }

    public <T> TypeConverterRegistry registerUnconverter(String type, Function<T, String> unconverter) {
        unconverters.put(type, unconverter);
        return this;
    }

    public static TypeConverterRegistry get() {
        return INSTANCE;
    }
}
