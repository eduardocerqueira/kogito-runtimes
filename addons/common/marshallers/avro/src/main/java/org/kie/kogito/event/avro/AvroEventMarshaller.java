package org.kie.kogito.event.avro;

import java.io.IOException;

import org.kie.kogito.event.EventMarshaller;

public class AvroEventMarshaller implements EventMarshaller<byte[]> {

    private final AvroIO avroUtils;

    public AvroEventMarshaller(AvroIO avroUtils) {
        this.avroUtils = avroUtils;
    }

    @Override
    public <T> byte[] marshall(T dataEvent) throws IOException {
        return avroUtils.writeObject(dataEvent);
    }
}
