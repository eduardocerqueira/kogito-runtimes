package org.kie.kogito.persistence;

import java.io.IOException;

import org.infinispan.protostream.MessageMarshaller;

public class BooleanProtostreamBaseMarshaller implements MessageMarshaller<Boolean> {

    @Override
    public Class<Boolean> getJavaClass() {
        return Boolean.class;
    }

    @Override
    public String getTypeName() {
        return "kogito.Boolean";
    }

    @Override
    public Boolean readFrom(ProtoStreamReader reader) throws IOException {
        return reader.readBoolean("data");
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Boolean data) throws IOException {
        writer.writeBoolean("data", data);
    }
}
