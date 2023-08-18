package org.kie.kogito.monitoring.core.quarkus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.junit.jupiter.api.Test;
import org.kie.kogito.monitoring.core.common.system.interceptor.MetricsInterceptor;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QuarkusMetricsInterceptorTest {

    @Test
    public void filter() {
        commonMetricInterceptorFilter(Arrays.asList("url1", "url2"), 200, "url1");
        commonMetricInterceptorFilter(Collections.singletonList("/url2"), 200, "/url2");
        commonMetricInterceptorFilter(Collections.emptyList(), 404, null);
        commonMetricInterceptorFilter(Collections.emptyList(), 200, null);
        commonMetricInterceptorFilter(Collections.singletonList("/url2"), 404, "/url2");
    }

    private void commonMetricInterceptorFilter(List<String> matchedUris, int statusCode, String expectedMatchedUrl) {
        ContainerRequestContext requestMock = mock(ContainerRequestContext.class);
        ContainerResponseContext responseMock = mock(ContainerResponseContext.class);
        UriInfo uriInfoMock = mock(UriInfo.class);
        Response.StatusType statusInfoMock = mock(Response.StatusType.class);

        when(requestMock.getUriInfo()).thenReturn(uriInfoMock);
        when(uriInfoMock.getMatchedURIs()).thenReturn(matchedUris);
        when(responseMock.getStatusInfo()).thenReturn(statusInfoMock);
        when(statusInfoMock.getStatusCode()).thenReturn(statusCode);

        MetricsInterceptor metricsInterceptor = mock(MetricsInterceptor.class);

        final ArgumentCaptor<String> matchedUrl = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<Integer> statusCodeCaptor = ArgumentCaptor.forClass(int.class);

        QuarkusMetricsInterceptor interceptor = new QuarkusMetricsInterceptor(metricsInterceptor);

        interceptor.filter(requestMock, responseMock);

        verify(metricsInterceptor, times(1)).filter(matchedUrl.capture(), statusCodeCaptor.capture());

        List<String> endpoints = matchedUrl.getAllValues();
        assertThat(endpoints).hasSize(1);
        assertThat(endpoints.get(0)).isEqualTo(expectedMatchedUrl);

        List<Integer> statusCodes = statusCodeCaptor.getAllValues();
        assertThat(statusCodes).hasSize(1);
        assertThat(statusCodes.get(0)).isEqualTo(statusCode);
    }
}
