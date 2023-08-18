package org.kie.kogito.addons.quarkus.k8s;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.kie.kogito.addons.k8s.EndpointDiscovery;
import org.kie.kogito.addons.quarkus.k8s.workitems.QuarkusDiscoveredEndpointCaller;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class EndpointCallerProducer {

    @Inject
    ObjectMapper objectMapper;

    @Produces
    @Singleton
    @Default
    public QuarkusDiscoveredEndpointCaller endpointCaller(EndpointDiscovery defaultQuarkusEndpointDiscovery) {
        final QuarkusDiscoveredEndpointCaller endpointCaller = new QuarkusDiscoveredEndpointCaller(objectMapper);
        endpointCaller.setEndpointDiscovery(defaultQuarkusEndpointDiscovery);
        return endpointCaller;
    }

}
