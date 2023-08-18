package org.jbpm.process.instance.impl.util;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;

public class TypeTransformer {

    private ObjectMapper mapper;
    private ClassLoader classLoader;

    public TypeTransformer() {
        this(TypeTransformer.class.getClassLoader());
    }

    public TypeTransformer(ClassLoader classLoader) {
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        this.classLoader = classLoader;
    }

    private Object transform(Object toMarshal, Class<?> targetClazz, ClassLoader currentClassLoader, String className) throws ClassNotFoundException, IOException {
        JavaParser parser = new JavaParser();
        ParseResult<Type> unit = parser.parseType(className);
        if (!unit.isSuccessful()) {
            return toMarshal;
        }
        ClassOrInterfaceType type = (ClassOrInterfaceType) unit.getResult().get();
        if (Collection.class.isAssignableFrom(targetClazz) && type.getTypeArguments().isPresent()) {
            // it is a generic so we try to read it.
            ClassOrInterfaceType argument = (ClassOrInterfaceType) type.getTypeArguments().get().get(0);
            Class<?> genericType = currentClassLoader.loadClass(toString(argument));
            JavaType targetGenericType = mapper.getTypeFactory().constructCollectionType(List.class, genericType);
            return mapper.convertValue(toMarshal, targetGenericType);
        }
        return mapper.convertValue(toMarshal, targetClazz);
    }

    public Object transform(Object toMarshal, Class<?> targetClass) throws IOException, ClassNotFoundException {
        return transform(toMarshal, targetClass, targetClass.getClassLoader(), targetClass.getName());
    }

    public Object transform(Object toMarshal, String className) throws ClassNotFoundException, IOException {
        return transform(toMarshal, classLoader.loadClass(className), classLoader, className);
    }

    private String toString(ClassOrInterfaceType type) {
        StringBuilder str = new StringBuilder();
        type.getScope().ifPresent(s -> str.append(s.asString()).append("."));
        str.append(type.getNameAsString());
        return str.toString();
    }
}