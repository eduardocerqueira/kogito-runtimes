package org.kie.kogito.codegen.api;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.codegen.api.AddonsConfig.DEFAULT;
import static org.kie.kogito.codegen.api.AddonsConfig.builder;

public class AddonsConfigTest {

    @Test
    public void allAddonsAreDisabledInDefaultConfiguration() {
        AddonsConfig addonsConfig = DEFAULT;
        assertThat(addonsConfig.useMonitoring()).isFalse();
        assertThat(addonsConfig.useTracing()).isFalse();
        assertThat(addonsConfig.usePersistence()).isFalse();
        assertThat(addonsConfig.useCloudEvents()).isFalse();
        assertThat(addonsConfig.usePersistence()).isFalse();
        assertThat(addonsConfig.useCloudEvents()).isFalse();
        assertThat(addonsConfig.useProcessSVG()).isFalse();
    }

    @Test
    public void addonsAreProperlyActivated() {
        ;
        assertThat(DEFAULT.useMonitoring()).isFalse();
        assertThat(builder().withMonitoring(true).build().useMonitoring()).isTrue();

        assertThat(DEFAULT.usePrometheusMonitoring()).isFalse();
        assertThat(builder().withPrometheusMonitoring(true).build().usePrometheusMonitoring()).isTrue();

        assertThat(DEFAULT.useTracing()).isFalse();
        assertThat(builder().withTracing(true).build().useTracing()).isTrue();

        assertThat(DEFAULT.usePersistence()).isFalse();
        assertThat(builder().withPersistence(true).build().usePersistence()).isTrue();

        assertThat(DEFAULT.useCloudEvents()).isFalse();
        assertThat(builder().withCloudEvents(true).build().useCloudEvents()).isTrue();

        assertThat(DEFAULT.useExplainability()).isFalse();
        assertThat(builder().withExplainability(true).build().useExplainability()).isTrue();

        assertThat(DEFAULT.useProcessSVG()).isFalse();
        assertThat(builder().withProcessSVG(true).build().useProcessSVG()).isTrue();
    }
}
