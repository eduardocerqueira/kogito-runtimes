package org.kie.kogito.testcontainers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class KogitoKafkaContainerTest {

    private static final int MAPPED_PORT = 11113;
    private static final String IMAGE = "vectorized/redpanda:latest";

    private KogitoKafkaContainer container;

    @BeforeEach
    public void setup() {
        System.setProperty(KogitoKafkaContainer.KAFKA_PROPERTY, IMAGE);
        container = spy(new KogitoKafkaContainer());
        doNothing().when(container).start();
        doNothing().when(container).setDockerImageName(IMAGE);
    }

    @Test
    public void shouldGetResourceName() {
        assertThat(container.getResourceName()).isEqualTo(KogitoKafkaContainer.NAME);
    }

    @Test
    public void shouldGetMapperPort() {
        doReturn(MAPPED_PORT).when(container).getMappedPort(KogitoKafkaContainer.KAFKA_PORT);
        assertThat(container.getMappedPort()).isEqualTo(MAPPED_PORT);
    }

}
