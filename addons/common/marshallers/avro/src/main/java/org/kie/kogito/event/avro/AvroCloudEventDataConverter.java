package org.kie.kogito.event.avro;

import java.io.IOException;

import org.kie.kogito.event.impl.AbstractCloudEventDataConverter;

public class AvroCloudEventDataConverter<O> extends AbstractCloudEventDataConverter<O> {

    private final AvroIO avroUtils;

    protected AvroCloudEventDataConverter(AvroIO avroUtils, Class<O> targetClass) {
        super(avroUtils.getObjectMapper(), targetClass);
        this.avroUtils = avroUtils;
    }

    @Override
    protected O toValue(byte[] value) throws IOException {
        return avroUtils.readObject(value, targetClass);
    }
}
