package org.kie.kogito.persistence;

import java.io.IOException;

import org.infinispan.protostream.MessageMarshaller;

public class DoubleProtostreamBaseMarshaller implements MessageMarshaller<Double> {

    @Override
    public Class<Double> getJavaClass() {
        return Double.class;
    }

    @Override
    public String getTypeName() {
        return "kogito.Double";
    }

    @Override
    public Double readFrom(ProtoStreamReader reader) throws IOException {
        return reader.readDouble("data");
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Double data) throws IOException {
        writer.writeDouble("data", data);
    }
}
