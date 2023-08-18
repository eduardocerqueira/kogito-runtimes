package org.kie.kogito.addons.quarkus.kubernetes;

import javax.inject.Inject;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.kie.kogito.addons.k8s.EndpointDiscovery;
import org.kie.kogito.addons.k8s.workitems.AbstractDiscoveredEndpointCaller;

import io.quarkus.test.QuarkusUnitTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KubernetesAddOnTest {

    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .overrideConfigKey("quarkus.devservices.enabled", "false")
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class));

    @Inject
    EndpointDiscovery endpointDiscovery;

    @Inject
    AbstractDiscoveredEndpointCaller endpointCaller;

    @Test
    void verifyBeanProcessorsAreInjected() {
        assertNotNull(endpointCaller);
        assertNotNull(endpointDiscovery);
    }

}
