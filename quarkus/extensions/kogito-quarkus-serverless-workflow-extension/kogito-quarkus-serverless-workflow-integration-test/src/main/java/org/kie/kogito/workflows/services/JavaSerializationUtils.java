package org.kie.kogito.workflows.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

class JavaSerializationUtils {

    static <T> T fromBytes(byte[] bytes, Class<T> targetClass, ObjectMapper objectMapper) throws IOException {
        try (ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            Object value = is.readObject();
            if (targetClass.isInstance(value)) {
                return targetClass.cast(value);
            } else {
                return objectMapper.convertValue(value, targetClass);
            }
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    private JavaSerializationUtils() {
    }
}
