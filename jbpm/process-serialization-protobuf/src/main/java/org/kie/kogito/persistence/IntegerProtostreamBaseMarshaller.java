package org.kie.kogito.persistence;

import java.io.IOException;

import org.infinispan.protostream.MessageMarshaller;

public class IntegerProtostreamBaseMarshaller implements MessageMarshaller<Integer> {

    @Override
    public Class<Integer> getJavaClass() {
        return Integer.class;
    }

    @Override
    public String getTypeName() {
        return "kogito.Integer";
    }

    @Override
    public Integer readFrom(ProtoStreamReader reader) throws IOException {
        return reader.readInt("data");
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Integer data) throws IOException {
        writer.writeInt("data", data);
    }
}
