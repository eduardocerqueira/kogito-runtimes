package org.kie.kogito.testcontainers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class KogitoMongoDBContainerTest {

    private static final int MAPPED_PORT = 27020;

    private KogitoMongoDBContainer container;

    @BeforeEach
    public void setup() {
        container = spy(new KogitoMongoDBContainer());
        doNothing().when(container).start();
    }

    @Test
    void shouldGetResourceName() {
        assertThat(container.getResourceName()).isEqualTo(KogitoMongoDBContainer.NAME);
    }

    @Test
    void shouldGetMapperPort() {
        doReturn(MAPPED_PORT).when(container).getMappedPort(KogitoMongoDBContainer.MONGODB_INTERNAL_PORT);
        assertThat(container.getMappedPort()).isEqualTo(MAPPED_PORT);
    }

    @Test
    void shouldGetDockerImageName() {
        assertThat(container.getDockerImageName()).isEqualTo(System.getProperty(Constants.CONTAINER_NAME_PREFIX + KogitoMongoDBContainer.NAME));
    }
}
