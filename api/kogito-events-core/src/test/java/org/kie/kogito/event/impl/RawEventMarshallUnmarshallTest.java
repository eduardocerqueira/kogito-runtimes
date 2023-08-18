package org.kie.kogito.event.impl;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.kie.kogito.event.impl.DataEventTestUtils.getRawEvent;
import static org.kie.kogito.event.impl.DataEventTestUtils.testEventMarshalling;

class RawEventMarshallUnmarshallTest {

    private static ObjectMapper mapper;

    @BeforeAll
    static void init() {
        mapper = ObjectMapperFactory.get();
    }

    @Test
    void testStringMarshaller() throws IOException {
        testEventMarshalling(getRawEvent(), new StringEventMarshaller(mapper), new JacksonEventDataUnmarshaller<>(mapper));
    }

    @Test
    void testObjectMarshaller() throws IOException {
        testEventMarshalling(getRawEvent(), new NoOpEventMarshaller(), new JacksonEventDataUnmarshaller<>(mapper));
    }

    @Test
    void testByteArrayMarshaller() throws IOException {
        testEventMarshalling(getRawEvent(), new ByteArrayEventMarshaller(mapper), new JacksonEventDataUnmarshaller<>(mapper));
    }
}
