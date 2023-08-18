package org.kie.kogito.persistence;

import java.io.IOException;
import java.util.Date;

import org.infinispan.protostream.MessageMarshaller;

public class DateProtostreamBaseMarshaller implements MessageMarshaller<Date> {

    @Override
    public Class<Date> getJavaClass() {
        return Date.class;
    }

    @Override
    public String getTypeName() {
        return "kogito.Date";
    }

    @Override
    public Date readFrom(ProtoStreamReader reader) throws IOException {
        return reader.readDate("data");
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Date data) throws IOException {
        writer.writeDate("data", data);
    }
}
