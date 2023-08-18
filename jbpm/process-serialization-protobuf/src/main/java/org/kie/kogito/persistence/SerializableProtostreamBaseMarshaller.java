package org.kie.kogito.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.infinispan.protostream.MessageMarshaller;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerException;

public class SerializableProtostreamBaseMarshaller implements MessageMarshaller<Serializable> {

    @Override
    public Class<? extends Serializable> getJavaClass() {
        return Serializable.class;
    }

    @Override
    public String getTypeName() {
        return "kogito.Serializable";
    }

    @Override
    public Serializable readFrom(ProtoStreamReader reader) throws IOException {
        return (Serializable) readObject(reader.readBytes("data"));
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Serializable serializable) throws IOException {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(stream)) {
            out.writeObject(serializable);
            writer.writeBytes("data", stream.toByteArray());
        } catch (IOException e) {
            throw new ProcessInstanceMarshallerException("Not possible to marshall value: " + serializable, e);
        }
    }

    private Object readObject(byte[] data) {
        try (InputStream is = new ByteArrayInputStream(data); ObjectInputStream ois = new ObjectInputStream(is)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ProcessInstanceMarshallerException("Unexpected error while trying to unmarshall object", e);
        }
    }

}
