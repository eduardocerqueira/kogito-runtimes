package org.kie.kogito.event.avro;

import java.io.IOException;

import org.kie.kogito.event.EventUnmarshaller;

public class AvroEventUnmarshaller implements EventUnmarshaller<byte[]> {

    private final AvroIO avroUtils;

    public AvroEventUnmarshaller(AvroIO avroUtils) {
        super();
        this.avroUtils = avroUtils;
    }

    @Override
    public <T> T unmarshall(byte[] input, Class<T> outputClass, Class<?>... parametrizedClasses) throws IOException {
        return avroUtils.readObject(input, outputClass, parametrizedClasses);
    }
}
