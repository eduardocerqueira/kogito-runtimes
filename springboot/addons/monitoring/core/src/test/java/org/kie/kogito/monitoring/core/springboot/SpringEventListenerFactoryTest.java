package org.kie.kogito.monitoring.core.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.monitoring.core.common.mock.MockedConfigBean;
import org.kie.kogito.monitoring.core.common.process.MetricsProcessEventListener;
import org.kie.kogito.monitoring.core.common.rule.RuleMetricsListenerConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SpringEventListenerFactoryTest {

    SpringbootEventListenerFactory factory;

    @BeforeEach
    public void init() {
        factory = new SpringbootEventListenerFactory(new MockedConfigBean());
    }

    @Test
    public void produceRuleListener() {
        assertThat(factory.produceRuleListener()).isInstanceOf(RuleMetricsListenerConfig.class);
    }

    @Test
    public void produceProcessListener() {
        assertThat(factory.produceProcessListener()).isInstanceOf(MetricsProcessEventListener.class);
    }
}
