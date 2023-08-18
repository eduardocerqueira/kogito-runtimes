package org.kie.kogito.addons.k8s.workitems;

import java.util.Collections;
import java.util.Map;

import org.kie.kogito.addons.k8s.Endpoint;
import org.kie.kogito.addons.k8s.EndpointDiscovery;
import org.kie.kogito.addons.k8s.EndpointQueryKey;
import org.kie.kogito.addons.k8s.LocalEndpointDiscovery;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MockDiscoveredEndpointCaller extends AbstractDiscoveredEndpointCaller {

    private final LocalEndpointDiscovery endpointDiscovery = new LocalEndpointDiscovery();

    public final static Map<String, String> SERVICE_LABELS = Collections.singletonMap("app", null);
    public final static String NAMESPACE = "test";

    public MockDiscoveredEndpointCaller(final String endpointURL) {
        super(new ObjectMapper());
        this.endpointDiscovery.addCache(new EndpointQueryKey(NAMESPACE, SERVICE_LABELS), new Endpoint(endpointURL));
    }

    @Override
    protected EndpointDiscovery getEndpointDiscovery() {
        return endpointDiscovery;
    }
}
