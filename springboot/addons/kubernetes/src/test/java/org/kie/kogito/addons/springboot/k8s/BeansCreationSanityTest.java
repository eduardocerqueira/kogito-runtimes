package org.kie.kogito.addons.springboot.k8s;

import org.junit.jupiter.api.Test;
import org.kie.kogito.addons.k8s.EndpointDiscovery;
import org.kie.kogito.addons.k8s.workitems.AbstractDiscoveredEndpointCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Very basic check to verify that our beans are being created.
 * No actions to perform since we don't have support to the Kubernetes mocked server natively on SB.
 */
@SpringBootTest(classes = { App.class })
public class BeansCreationSanityTest {

    @Autowired
    EndpointDiscovery endpointDiscovery;

    @Autowired
    AbstractDiscoveredEndpointCaller discoveredEndpointCaller;

    @Test
    void verifyBeansCreation() {
        assertNotNull(endpointDiscovery);
        assertNotNull(discoveredEndpointCaller);
    }

}
