package org.kie.kogito.persistence;

import java.io.IOException;

import org.infinispan.protostream.MessageMarshaller;

public class FloatProtostreamBaseMarshaller implements MessageMarshaller<Float> {

    @Override
    public Class<Float> getJavaClass() {
        return Float.class;
    }

    @Override
    public String getTypeName() {
        return "kogito.Float";
    }

    @Override
    public Float readFrom(ProtoStreamReader reader) throws IOException {
        return reader.readFloat("data");
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Float data) throws IOException {
        writer.writeFloat("data", data);
    }
}
