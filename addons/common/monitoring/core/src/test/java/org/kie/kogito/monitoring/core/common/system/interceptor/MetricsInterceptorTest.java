package org.kie.kogito.monitoring.core.common.system.interceptor;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kie.kogito.monitoring.core.common.system.metrics.SystemMetricsCollector;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MetricsInterceptorTest {

    @Test
    void testUrlEncodedStringFromFailure() throws Exception {
        commonMetricInterceptorFilter("myUrl", 404, "NOT FOUND");
        commonMetricInterceptorFilter("myUrl", 200, "myUrl");
        commonMetricInterceptorFilter("/myUrl", 404, "NOT FOUND");
        commonMetricInterceptorFilter("/myUrl", 200, "myUrl");
    }

    private void commonMetricInterceptorFilter(String endpoint, int statusCode, String expectedEndpoint) {
        SystemMetricsCollector systemMetricsCollector = Mockito.mock(SystemMetricsCollector.class);
        MetricsInterceptor metricsInterceptor = new MetricsInterceptor(systemMetricsCollector);
        metricsInterceptor.filter(endpoint, statusCode);

        final ArgumentCaptor<String> endpointCaptor = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> statusCodeCaptor = ArgumentCaptor.forClass(String.class);
        verify(systemMetricsCollector, times(1)).registerStatusCodeRequest(endpointCaptor.capture(), statusCodeCaptor.capture());

        List<String> endpoints = endpointCaptor.getAllValues();
        assertThat(endpoints).isNotEmpty().hasSize(1);
        assertThat(endpoints).containsExactly(expectedEndpoint);

        List<String> statusCodes = statusCodeCaptor.getAllValues();
        assertThat(statusCodes).isNotEmpty().hasSize(1);
        String statusCodeString = String.valueOf(statusCode);
        assertThat(statusCodes).containsExactly(statusCodeString);
    }
}
