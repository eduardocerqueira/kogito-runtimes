package org.kie.kogito.event.impl;

import java.io.IOException;

import org.kie.kogito.event.CloudEventMarshaller;
import org.kie.kogito.event.CloudEventUnmarshaller;
import org.kie.kogito.event.CloudEventUnmarshallerFactory;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.DataEventFactory;
import org.kie.kogito.event.EventMarshaller;
import org.kie.kogito.event.EventUnmarshaller;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;

import com.fasterxml.jackson.databind.JsonNode;

import static org.assertj.core.api.Assertions.assertThat;

public class DataEventTestUtils {

    public static <T> void assertDataEventEquals(DataEvent<T> event, DataEvent<T> targetEvent) {
        assertThat(targetEvent.getAttributeNames()).isEqualTo(event.getAttributeNames());
        for (String attrName : event.getAttributeNames()) {
            assertThat(targetEvent.getAttribute(attrName)).isEqualTo(event.getAttribute(attrName));
        }
        assertThat(targetEvent.getExtensionNames()).isEqualTo(event.getExtensionNames());
        for (String extensionName : event.getExtensionNames()) {
            assertThat(targetEvent.getExtension(extensionName)).isEqualTo(event.getExtension(extensionName));
        }
        assertThat(targetEvent.getData()).isEqualTo(event.getData());
    }

    public static <T, V> void testCloudEventMarshalling(DataEvent<V> event, Class<V> clazz, CloudEventMarshaller<T> marshaller, CloudEventUnmarshallerFactory<T> unmarshallerFactory)
            throws IOException {
        CloudEventUnmarshaller<T, V> unmarshaller = unmarshallerFactory.unmarshaller(clazz);
        DataEvent<V> targetEvent =
                DataEventFactory.from(unmarshaller.cloudEvent().convert(marshaller.marshall(event.asCloudEvent(marshaller.cloudEventDataFactory()))), unmarshaller.data());
        assertDataEventEquals(event, targetEvent);
    }

    public static <T> void testEventMarshalling(Object event, EventMarshaller<T> marshaller, EventUnmarshaller<T> unmarshaller) throws IOException {
        assertThat(unmarshaller.unmarshall(marshaller.marshall(event), event.getClass())).isEqualTo(event);
    }

    public static DataEvent<JsonNode> getJsonNodeCloudEvent() {
        return new TestCloudEvent<>(getJsonNode(), "pepa");
    }

    public static JsonNode getJsonNode() {
        return ObjectMapperFactory.get().createObjectNode().put("name", "pepe").put("salary", 12312);
    }

    public static TestEvent getRawEvent() {
        return new TestEvent("pepe");
    }

    public static DataEvent<TestEvent> getPojoCloudEvent() {
        return new TestCloudEvent<>(getRawEvent(), "pepa");
    }
}
