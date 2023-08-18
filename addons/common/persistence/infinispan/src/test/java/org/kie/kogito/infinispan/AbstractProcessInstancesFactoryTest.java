package org.kie.kogito.infinispan;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.RemoteCacheManagerAdmin;
import org.junit.jupiter.api.Test;
import org.kie.kogito.process.Process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AbstractProcessInstancesFactoryTest {

    @Test
    void testCreate() {
        RemoteCacheManager cacheManager = mock(RemoteCacheManager.class);
        when(cacheManager.administration()).thenReturn(mock(RemoteCacheManagerAdmin.class));
        AbstractProcessInstancesFactory factory = new AbstractProcessInstancesFactory(cacheManager, false, null) {
        };

        assertThat(factory.createProcessInstances(mock(Process.class))).isNotNull();
    }
}
