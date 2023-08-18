package org.kie.kogito.persistence;

import java.io.IOException;
import java.time.Instant;

import org.infinispan.protostream.MessageMarshaller;

public class InstantProtostreamBaseMarshaller implements MessageMarshaller<Instant> {

    @Override
    public Class<? extends Instant> getJavaClass() {
        return Instant.class;
    }

    @Override
    public String getTypeName() {
        return "kogito.Instant";
    }

    @Override
    public Instant readFrom(ProtoStreamReader reader) throws IOException {
        return reader.readInstant("data");
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Instant instant) throws IOException {
        writer.writeInstant("data", instant);
    }

}
