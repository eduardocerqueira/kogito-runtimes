package org.kie.kogito.event.avro;

import java.io.IOException;

import org.kie.kogito.event.impl.AbstractCloudEventDataFactory;

public class AvroCloudEventDataFactory<T> extends AbstractCloudEventDataFactory<T> {

    private final AvroIO avroUtils;

    public AvroCloudEventDataFactory(AvroIO avroUtils) {
        this.avroUtils = avroUtils;
    }

    @Override
    protected byte[] toBytes(T event) throws IOException {
        return avroUtils.writeObject(event);
    }
}
