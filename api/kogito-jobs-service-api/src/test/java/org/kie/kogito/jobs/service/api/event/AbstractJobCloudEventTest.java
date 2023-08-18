package org.kie.kogito.jobs.service.api.event;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.jobs.service.api.event.TestConstants.*;

abstract class AbstractJobCloudEventTest<E extends JobCloudEvent<?>> {

    abstract E buildEvent();

    abstract String eventType();

    @Test
    void builder() {
        E event = buildEvent();
        assertFields(event);
    }

    void assertFields(E event) {
        assertThat(event.getId()).isEqualTo(ID);
        assertThat(event.getSpecVersion()).isEqualTo(SPEC_VERSION);
        assertThat(event.getSource()).isEqualTo(SOURCE);
        assertThat(event.getType()).isEqualTo(eventType());
        assertThat(event.getTime()).isEqualTo(TIME);
        assertThat(event.getSubject()).isEqualTo(SUBJECT);
        assertThat(event.getDataSchema()).isEqualTo(DATA_SCHEMA);
    }
}
