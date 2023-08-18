package org.kie.kogito.addons.springboot.k8s.workitems;

import org.kie.kogito.addons.k8s.EndpointDiscovery;
import org.kie.kogito.addons.k8s.workitems.AbstractDiscoveredEndpointCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SpringDiscoveredEndpointCaller extends AbstractDiscoveredEndpointCaller {

    private EndpointDiscovery endpointDiscovery;

    public SpringDiscoveredEndpointCaller(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected EndpointDiscovery getEndpointDiscovery() {
        return endpointDiscovery;
    }

    @Autowired
    public void setEndpointDiscovery(EndpointDiscovery endpointDiscovery) {
        this.endpointDiscovery = endpointDiscovery;
    }
}
