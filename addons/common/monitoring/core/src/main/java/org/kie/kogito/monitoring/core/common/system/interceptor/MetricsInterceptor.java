package org.kie.kogito.monitoring.core.common.system.interceptor;

import org.kie.kogito.monitoring.core.common.system.metrics.SystemMetricsCollector;

public class MetricsInterceptor {

    private final SystemMetricsCollector systemMetricsCollector;

    public MetricsInterceptor(SystemMetricsCollector systemMetricsCollector) {
        this.systemMetricsCollector = systemMetricsCollector;
    }

    public void filter(String matchedUrl, int statusCode) {
        String stringStatusCode = String.valueOf(statusCode);
        if (statusCode != 404) {
            String cleanUrl = cleanUrl(matchedUrl);
            systemMetricsCollector.registerStatusCodeRequest(cleanUrl, stringStatusCode);
        } else // Log the number of requests that did not match any Uri -> 404 not found.
        {
            systemMetricsCollector.registerStatusCodeRequest("NOT FOUND", stringStatusCode);
        }
    }

    private String cleanUrl(String matchedUrl) {
        if (matchedUrl != null && matchedUrl.startsWith("/")) {
            return matchedUrl.substring(1);
        }
        return matchedUrl;
    }
}
