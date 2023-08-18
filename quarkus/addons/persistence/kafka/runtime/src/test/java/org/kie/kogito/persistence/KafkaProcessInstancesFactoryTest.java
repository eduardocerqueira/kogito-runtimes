package org.kie.kogito.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.persistence.kafka.KafkaStreamsStateListener;
import org.kie.kogito.process.Process;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaProcessInstancesFactoryTest {

    @Mock
    KafkaStreamsStateListener listener;

    @Spy
    KafkaProcessInstancesFactory factory;

    @Test
    public void testCreateProcessInstances() {
        factory.setStateListener(listener);

        assertThat(factory.createProcessInstances(mock(Process.class))).isNotNull();

        verify(listener).addProcessInstances(any());
    }
}
