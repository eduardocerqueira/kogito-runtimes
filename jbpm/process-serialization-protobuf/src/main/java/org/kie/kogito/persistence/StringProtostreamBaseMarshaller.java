package org.kie.kogito.persistence;

import java.io.IOException;

import org.infinispan.protostream.MessageMarshaller;

public class StringProtostreamBaseMarshaller implements MessageMarshaller<String> {

    @Override
    public Class<String> getJavaClass() {
        return String.class;
    }

    @Override
    public String getTypeName() {
        return "kogito.String";
    }

    @Override
    public String readFrom(ProtoStreamReader reader) throws IOException {
        return reader.readString("data");
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, String data) throws IOException {
        writer.writeString("data", data);
    }
}
