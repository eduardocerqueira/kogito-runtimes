package org.kie.kogito.event.avro;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.kogito.event.impl.TestEvent;

import com.fasterxml.jackson.databind.JsonNode;

import static org.kie.kogito.event.impl.DataEventTestUtils.getJsonNode;
import static org.kie.kogito.event.impl.DataEventTestUtils.getJsonNodeCloudEvent;
import static org.kie.kogito.event.impl.DataEventTestUtils.getPojoCloudEvent;
import static org.kie.kogito.event.impl.DataEventTestUtils.getRawEvent;
import static org.kie.kogito.event.impl.DataEventTestUtils.testCloudEventMarshalling;
import static org.kie.kogito.event.impl.DataEventTestUtils.testEventMarshalling;

class AvroMarshallUnmarshallTest {

    private static AvroIO avroUtils;

    @BeforeAll
    static void init() throws IOException {
        avroUtils = new AvroIO();
    }

    @Test
    void testCloudEventMarshaller() throws IOException {
        testCloudEventMarshalling(getPojoCloudEvent(), TestEvent.class, new AvroCloudEventMarshaller(avroUtils), new AvroCloudEventUnmarshallerFactory(avroUtils));
    }

    @Test
    void testEventMarshaller() throws IOException {
        testEventMarshalling(getRawEvent(), new AvroEventMarshaller(avroUtils), new AvroEventUnmarshaller(avroUtils));
    }

    @Test
    void testJsonNodeMarshaller() throws IOException {
        testEventMarshalling(getJsonNode(), new AvroEventMarshaller(avroUtils), new AvroEventUnmarshaller(avroUtils));
    }

    @Test
    void testJsonNodeCloudEventMarshaller() throws IOException {
        testCloudEventMarshalling(getJsonNodeCloudEvent(), JsonNode.class, new AvroCloudEventMarshaller(avroUtils), new AvroCloudEventUnmarshallerFactory(avroUtils));
    }
}
