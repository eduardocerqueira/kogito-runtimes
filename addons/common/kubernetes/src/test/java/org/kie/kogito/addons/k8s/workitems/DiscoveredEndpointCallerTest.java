package org.kie.kogito.addons.k8s.workitems;

import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.process.workitems.impl.KogitoWorkItemImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TargetEndpointsMockServerExtension.class)
public class DiscoveredEndpointCallerTest {

    AbstractDiscoveredEndpointCaller endpointCaller;

    public DiscoveredEndpointCallerTest(final String endpointURL) {
        this.endpointCaller = new MockDiscoveredEndpointCaller(endpointURL);
    }

    @Test
    void testDiscoveryAndCall() {
        final KogitoWorkItemImpl workItem = new KogitoWorkItemImpl();
        workItem.setParameter("discovery", "app");
        final Map<String, Object> response = this.endpointCaller.discoverAndCall(workItem, MockDiscoveredEndpointCaller.NAMESPACE, "discovery", HttpMethod.GET);
        assertThat(response).isNotNull()
                .containsEntry("response", "OK");
    }

}
