package org.kie.kogito.workflows.services;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.function.Function;

import org.kie.kogito.event.CloudEventMarshaller;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;

public class JavaSerializationMarshaller implements CloudEventMarshaller<byte[]> {

    @Override
    public byte[] marshall(CloudEvent dataEvent) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (DataOutputStream out = new DataOutputStream(bytes)) {
            out.writeUTF(dataEvent.getSpecVersion().toString());
            out.writeUTF(dataEvent.getId());
            out.writeUTF(dataEvent.getType());
            out.writeUTF(dataEvent.getSource().toString());
            writeOptional(out, dataEvent.getTime());
            writeOptional(out, dataEvent.getSubject());
            writeOptional(out, dataEvent.getDataSchema());
            writeOptional(out, dataEvent.getDataContentType());

            Set<String> extensionNames = dataEvent.getExtensionNames();
            out.writeShort(extensionNames.size());
            for (String extensionName : extensionNames) {
                out.writeUTF(extensionName);
                writeOptional(out, dataEvent.getExtension(extensionName));
            }
            CloudEventData data = dataEvent.getData();
            if (data != null) {
                out.writeBoolean(true);
                byte[] dataBytes = data.toBytes();
                out.write(dataBytes, 0, dataBytes.length);
            } else {
                out.writeBoolean(false);
            }
        }
        return bytes.toByteArray();
    }

    private void writeOptional(DataOutputStream out, Object object) throws IOException {
        if (object != null) {
            out.writeBoolean(true);
            out.writeUTF(object.toString());
        } else {
            out.writeBoolean(false);
        }
    }

    @Override
    public <T> Function<T, CloudEventData> cloudEventDataFactory() {
        return new JavaSerializationCloudEventDataFactory<>();
    }
}
