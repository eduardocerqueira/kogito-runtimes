package org.kie.kogito.testcontainers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.testcontainers.Constants.CONTAINER_NAME_PREFIX;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class InfinispanContainerTest {

    private static final int MAPPED_PORT = 11111;
    private static final String IMAGE = "my-infinispan-image";

    private KogitoInfinispanContainer container;

    @BeforeEach
    public void setup() {
        System.setProperty(CONTAINER_NAME_PREFIX + KogitoInfinispanContainer.NAME, IMAGE);
        container = spy(new KogitoInfinispanContainer());
        doNothing().when(container).start();
        doNothing().when(container).setDockerImageName(IMAGE);
    }

    @Test
    public void shouldAddDefaultSettings() {
        assertThat(container.getExposedPorts()).contains(KogitoInfinispanContainer.PORT);
    }

    @Test
    public void shouldGetResourceName() {
        assertThat(container.getResourceName()).isEqualTo(KogitoInfinispanContainer.NAME);
    }

    @Test
    public void shouldGetMapperPort() {
        doReturn(MAPPED_PORT).when(container).getMappedPort(KogitoInfinispanContainer.PORT);
        assertThat(container.getMappedPort()).isEqualTo(MAPPED_PORT);
    }

}
