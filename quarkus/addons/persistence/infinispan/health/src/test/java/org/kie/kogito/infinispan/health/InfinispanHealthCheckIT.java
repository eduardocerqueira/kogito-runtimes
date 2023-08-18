package org.kie.kogito.infinispan.health;

import javax.annotation.Resource;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.junit.jupiter.api.Test;
import org.kie.kogito.testcontainers.quarkus.InfinispanQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@QuarkusTestResource(InfinispanQuarkusTestResource.class)
public class InfinispanHealthCheckIT {

    private InfinispanHealthCheck healthCheck;

    @Inject
    Instance<RemoteCacheManager> instance;

    @Resource
    InfinispanQuarkusTestResource resource;

    @Test
    void testCall() throws Exception {
        resource.start();

        this.healthCheck = new InfinispanHealthCheck(instance);

        //testing Up
        HealthCheckResponse response = healthCheck.call();
        assertThat(response.getStatus()).isEqualTo(HealthCheckResponse.Status.UP);

        resource.stop();

        //testing Down
        HealthCheckResponse response2 = healthCheck.call();
        assertThat(response2.getStatus()).isEqualTo(HealthCheckResponse.Status.DOWN);
    }
}
