package org.kie.kogito.workflows.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.kie.kogito.event.impl.AbstractCloudEventDataFactory;

public class JavaSerializationCloudEventDataFactory<T> extends AbstractCloudEventDataFactory<T> {

    @Override
    protected byte[] toBytes(T object) throws IOException {
        return object instanceof byte[] ? (byte[]) object : convert(object);
    }

    protected static <T> byte[] convert(T object) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bytes)) {
            out.writeObject(object);
        }
        return bytes.toByteArray();
    }
}
