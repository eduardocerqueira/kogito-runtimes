package org.kie.kogito.monitoring.core.springboot;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.config.ConfigBean;
import org.kie.kogito.monitoring.core.common.Constants;
import org.kie.kogito.monitoring.core.common.system.interceptor.MetricsInterceptor;
import org.kie.kogito.monitoring.core.common.system.metrics.SystemMetricsCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.micrometer.core.instrument.Metrics;

@Configuration
public class SpringbootMetricsFilterRegister implements WebMvcConfigurer {

    @Value(value = "${" + Constants.HTTP_INTERCEPTOR_USE_DEFAULT + ":true}")
    boolean httpInterceptorUseDefault;

    ConfigBean configBean;

    @Autowired
    public SpringbootMetricsFilterRegister(ConfigBean configBean) {
        this.configBean = configBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (httpInterceptorUseDefault) {
            SystemMetricsCollector systemMetricsCollector = new SystemMetricsCollector(configBean.getGav().orElse(KogitoGAV.EMPTY_GAV), Metrics.globalRegistry);
            MetricsInterceptor metricsInterceptor = new MetricsInterceptor(systemMetricsCollector);
            registry.addInterceptor(new SpringbootMetricsInterceptor(metricsInterceptor));
        }
    }

    // for testing purpose
    void setHttpInterceptorUseDefault(boolean httpInterceptorUseDefault) {
        this.httpInterceptorUseDefault = httpInterceptorUseDefault;
    }
}
