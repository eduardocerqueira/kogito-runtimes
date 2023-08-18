package org.kie.kogito.monitoring.core.quarkus;

import java.util.List;

import javax.enterprise.inject.Instance;
import javax.ws.rs.core.FeatureContext;

import org.junit.jupiter.api.Test;
import org.kie.kogito.monitoring.core.common.mock.MockedConfigBean;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QuarkusMetricsFilterRegisterTest {

    @Test
    public void configure() {
        commonConfigure(true, 1);
        commonConfigure(false, 0);
    }

    private void commonConfigure(boolean httpInterceptorUseDefault, int numberOfTimes) {
        FeatureContext contextMock = mock(FeatureContext.class);
        QuarkusMetricsFilterRegister filterRegister = new QuarkusMetricsFilterRegister(new MockedConfigBean());

        @SuppressWarnings("unchecked")
        Instance<Boolean> instanceHttpInterceptorUseDefault = mock(Instance.class);
        when(instanceHttpInterceptorUseDefault.isResolvable()).thenReturn(true);
        when(instanceHttpInterceptorUseDefault.get()).thenReturn(httpInterceptorUseDefault);

        filterRegister.setHttpInterceptorUseDefault(instanceHttpInterceptorUseDefault);
        filterRegister.configure(null, contextMock);

        final ArgumentCaptor<Object> registerCaptor = ArgumentCaptor.forClass(Object.class);

        verify(contextMock, times(numberOfTimes)).register(registerCaptor.capture());

        if (httpInterceptorUseDefault) {
            List<Object> values = registerCaptor.getAllValues();
            assertThat(values).hasSize(1);
            assertThat(values.get(0)).isInstanceOf(QuarkusMetricsInterceptor.class);
        } else {
            assertThat(registerCaptor.getAllValues()).isEmpty();
        }
    }

}
