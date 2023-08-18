package org.kie.kogito.jackson.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonNodeConverterTest {
    @Test
    void testJsonStringConverter() {
        assertThat(new JsonNodeConverter().apply("{\"name\":\"javierito\"}")).isEqualTo(ObjectMapperFactory.get().createObjectNode().put("name", "javierito"));
    }
}
