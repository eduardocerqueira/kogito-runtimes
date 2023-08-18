package org.kie.kogito.monitoring.core.springboot;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.kie.kogito.monitoring.core.common.system.interceptor.MetricsInterceptor;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SpringMetricsInterceptorTest {

    @Test
    public void filter() throws Exception {
        commonMetricInterceptorFilter("url1", 200, "url1");
        commonMetricInterceptorFilter("/url2", 200, "/url2");
        commonMetricInterceptorFilter(null, 404, null);
        commonMetricInterceptorFilter("", 200, "");
        commonMetricInterceptorFilter("/url2", 404, "/url2");
    }

    private void commonMetricInterceptorFilter(String requestUri, int statusCode, String expectedMatchedUrl) throws Exception {
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);

        when(requestMock.getRequestURI()).thenReturn(requestUri);
        when(responseMock.getStatus()).thenReturn(statusCode);

        MetricsInterceptor metricsInterceptor = mock(MetricsInterceptor.class);

        final ArgumentCaptor<String> matchedUrl = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<Integer> statusCodeCaptor = ArgumentCaptor.forClass(int.class);

        SpringbootMetricsInterceptor interceptor = new SpringbootMetricsInterceptor(metricsInterceptor);

        interceptor.postHandle(requestMock, responseMock, null, null);

        verify(metricsInterceptor, times(1)).filter(matchedUrl.capture(), statusCodeCaptor.capture());

        List<String> endpoints = matchedUrl.getAllValues();
        assertThat(endpoints).hasSize(1);
        assertThat(endpoints.get(0)).isEqualTo(expectedMatchedUrl);

        List<Integer> statusCodes = statusCodeCaptor.getAllValues();
        assertThat(statusCodes).hasSize(1);
        assertThat(statusCodes.get(0)).isEqualTo(statusCode);
    }
}
