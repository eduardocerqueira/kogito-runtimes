package org.kie.kogito.addons.quarkus.k8s.workitems;

import org.kie.kogito.addons.k8s.EndpointDiscovery;
import org.kie.kogito.addons.k8s.workitems.AbstractDiscoveredEndpointCaller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class QuarkusDiscoveredEndpointCaller extends AbstractDiscoveredEndpointCaller {

    EndpointDiscovery endpointDiscovery;

    public QuarkusDiscoveredEndpointCaller(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected EndpointDiscovery getEndpointDiscovery() {
        return endpointDiscovery;
    }

    public void setEndpointDiscovery(EndpointDiscovery endpointDiscovery) {
        this.endpointDiscovery = endpointDiscovery;
    }
}
