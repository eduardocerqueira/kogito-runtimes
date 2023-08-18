package org.kie.kogito.event;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DataEventAttrBuilderTest {

    @Test
    void verifyEventSourceBeingGenerated() {
        final String processId = "the_cool_project";
        final String instanceId = UUID.randomUUID().toString();
        KogitoProcessInstance pi = mock(KogitoProcessInstance.class);
        when(pi.getProcessId()).thenReturn(processId);
        when(pi.getStringId()).thenReturn(instanceId);
        final String source = DataEventAttrBuilder.toSource(pi);
        assertThat(source).isNotBlank().contains(processId);
    }

    @Test
    void verifyEventTypeBeingGenerated() {
        final String channelName = "github";
        final String processId = "the_cool_project";
        final String type = DataEventAttrBuilder.toType(channelName, processId);
        assertThat(type).isNotBlank().contains(processId).contains(channelName).startsWith(AbstractDataEvent.TYPE_PREFIX);
    }

    @Test
    void verifyEventTypeBeingGeneratedWithProcessInstance() {
        final String channelName = "github";
        final String processId = "COOL_PROJECT";
        KogitoProcessInstance pi = mock(KogitoProcessInstance.class);
        when(pi.getProcessId()).thenReturn(processId);
        final String type = DataEventAttrBuilder.toType(channelName, pi);
        assertThat(type)
                .isNotBlank()
                .doesNotContain(processId)
                .contains(processId.toLowerCase())
                .contains(channelName)
                .startsWith(AbstractDataEvent.TYPE_PREFIX);
    }
}
