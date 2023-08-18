package org.kie.kogito.workflows.services;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URI;
import java.time.OffsetDateTime;

import org.kie.kogito.event.CloudEventUnmarshaller;
import org.kie.kogito.event.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import io.cloudevents.SpecVersion;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import io.vertx.core.buffer.Buffer;

public class JavaSerializationUnmarshaller<T> implements CloudEventUnmarshaller<Object, T> {

    private final ObjectMapper objectMapper;
    private final Class<T> javaDataClass;

    public JavaSerializationUnmarshaller(ObjectMapper objectMapper, Class<T> javaDataClass) {
        this.objectMapper = objectMapper;
        this.javaDataClass = javaDataClass;
    }

    @Override
    public Converter<Object, CloudEvent> cloudEvent() {
        return this::cloudEvent;
    }

    private CloudEvent cloudEvent(Object object) throws IOException {
        Buffer buffer = (Buffer) object;
        try (DataInputStream is = new DataInputStream(new ByteArrayInputStream(buffer.getBytes()))) {
            CloudEventBuilder builder = CloudEventBuilder.fromSpecVersion(SpecVersion.parse(is.readUTF()));
            builder.withId(is.readUTF());
            builder.withType(is.readUTF());
            builder.withSource(URI.create(is.readUTF()));
            if (is.readBoolean()) {
                builder.withTime(OffsetDateTime.parse(is.readUTF()));
            }
            if (is.readBoolean()) {
                builder.withSubject(is.readUTF());
            }
            if (is.readBoolean()) {
                builder.withDataSchema(URI.create(is.readUTF()));
            }
            if (is.readBoolean()) {
                builder.withDataContentType(is.readUTF());
            }

            int numExtensions = is.readShort();

            while (numExtensions-- > 0) {
                String extName = is.readUTF();
                if (is.readBoolean()) {
                    builder.withExtension(extName, is.readUTF());
                }
            }
            if (is.readBoolean()) {
                builder.withData(is.readAllBytes());
            }
            return builder.build();
        }
    }

    @Override
    public Converter<Object, CloudEventData> binaryCloudEvent() {
        return bytes -> PojoCloudEventData.wrap(JavaSerializationUtils.fromBytes(((Buffer) bytes).getBytes(), javaDataClass, objectMapper), JavaSerializationCloudEventDataFactory::convert);
    }

    @Override
    public Converter<CloudEventData, T> data() {
        return new JavaSerializationCloudEventDataConverter<>(objectMapper, javaDataClass);
    }

}
