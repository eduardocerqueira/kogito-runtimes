package org.kie.kogito.monitoring.core.springboot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kie.kogito.monitoring.core.common.system.interceptor.MetricsInterceptor;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SpringbootMetricsInterceptor implements HandlerInterceptor {

    private final MetricsInterceptor metricsInterceptor;

    public SpringbootMetricsInterceptor(MetricsInterceptor metricsInterceptor) {
        this.metricsInterceptor = metricsInterceptor;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, ModelAndView modelAndView) throws Exception {
        metricsInterceptor.filter(request.getRequestURI(), response.getStatus());
    }
}
