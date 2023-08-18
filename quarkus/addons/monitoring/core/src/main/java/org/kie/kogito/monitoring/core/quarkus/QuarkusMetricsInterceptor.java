package org.kie.kogito.monitoring.core.quarkus;

import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.kie.kogito.monitoring.core.common.system.interceptor.MetricsInterceptor;

public class QuarkusMetricsInterceptor implements ContainerResponseFilter {

    private final MetricsInterceptor metricsInterceptor;

    public QuarkusMetricsInterceptor(MetricsInterceptor metricsInterceptor) {
        this.metricsInterceptor = metricsInterceptor;
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) {
        List<String> matchedUris = requestContext.getUriInfo().getMatchedURIs();
        String matchedUrl = matchedUris.isEmpty() ? null : matchedUris.get(0);
        metricsInterceptor.filter(matchedUrl, responseContext.getStatusInfo().getStatusCode());
    }
}
