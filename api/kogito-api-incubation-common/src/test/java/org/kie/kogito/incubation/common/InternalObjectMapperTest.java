package org.kie.kogito.incubation.common;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kie.kogito.incubation.common.objectmapper.InternalObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class InternalObjectMapperTest {

    @Test
    public void testFastAsUsingCast() {
        DataContext ctx = new MapDataContext(Map.of("full name", "John Doe", "age", 47));

        MapDataContext converted = InternalObjectMapper.objectMapper().convertValue(ctx, MapDataContext.class);
        assertThat(converted).isSameAs(ctx);
    }
}
