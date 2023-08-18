package org.kie.kogito.monitoring.core.springboot;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kie.kogito.monitoring.core.common.mock.MockedConfigBean;
import org.mockito.ArgumentCaptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SpringMetricsFilterRegisterTest {

    @Test
    public void configure() {
        commonConfigure(true, 1);
        commonConfigure(false, 0);
    }

    private void commonConfigure(boolean httpInterceptorUseDefault, int numberOfTimes) {
        InterceptorRegistry registryMock = mock(InterceptorRegistry.class);
        SpringbootMetricsFilterRegister filterRegister = new SpringbootMetricsFilterRegister(new MockedConfigBean());

        filterRegister.setHttpInterceptorUseDefault(httpInterceptorUseDefault);
        filterRegister.addInterceptors(registryMock);

        final ArgumentCaptor<HandlerInterceptor> registerCaptor = ArgumentCaptor.forClass(HandlerInterceptor.class);

        verify(registryMock, times(numberOfTimes)).addInterceptor(registerCaptor.capture());

        if (httpInterceptorUseDefault) {
            List<HandlerInterceptor> values = registerCaptor.getAllValues();
            assertThat(values).hasSize(1);
            assertThat(values.get(0)).isInstanceOf(SpringbootMetricsInterceptor.class);
        } else {
            assertThat(registerCaptor.getAllValues()).isEmpty();
        }
    }

}
