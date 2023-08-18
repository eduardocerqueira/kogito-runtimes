package org.kie.kogito.events.mongodb.codec;

import org.junit.jupiter.api.Test;
import org.kie.kogito.event.process.ProcessInstanceDataEvent;
import org.kie.kogito.event.process.UserTaskInstanceDataEvent;
import org.kie.kogito.event.process.VariableInstanceDataEvent;

import static org.assertj.core.api.Assertions.assertThat;

class EventMongoDBCodecProviderTest {

    @Test
    void get() {
        EventMongoDBCodecProvider provider = new EventMongoDBCodecProvider();

        assertThat(provider.get(ProcessInstanceDataEvent.class, null).getClass()).isEqualTo(ProcessInstanceDataEventCodec.class);
        assertThat(provider.get(UserTaskInstanceDataEvent.class, null).getClass()).isEqualTo(UserTaskInstanceDataEventCodec.class);
        assertThat(provider.get(VariableInstanceDataEvent.class, null).getClass()).isEqualTo(VariableInstanceDataEventCodec.class);
        assertThat(provider.get(String.class, null)).isNull();
    }
}