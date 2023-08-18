package org.kie.kogito.persistence;

import java.io.IOException;

import org.infinispan.protostream.MessageMarshaller;

public class LongProtostreamBaseMarshaller implements MessageMarshaller<Long> {

    @Override
    public Class<Long> getJavaClass() {
        return Long.class;
    }

    @Override
    public String getTypeName() {
        return "kogito.Long";
    }

    @Override
    public Long readFrom(ProtoStreamReader reader) throws IOException {
        return reader.readLong("data");
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Long data) throws IOException {
        writer.writeLong("data", data);
    }
}
