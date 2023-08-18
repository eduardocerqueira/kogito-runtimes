package org.kie.kogito.codegen.sample.core;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;
import org.kie.kogito.Config;
import org.kie.kogito.StaticApplication;
import org.kie.kogito.StaticConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SampleRuntimeImplTest {

    Config config = new StaticConfig(null, new SampleConfigImpl(10));
    Application application = new StaticApplication(config);

    @Test
    void initApplication() {

        SampleRuntimeImpl sampleRuntime = new SampleRuntimeImpl(application);
        assertThat(sampleRuntime.config).isNotNull();

        sampleRuntime = new SampleRuntimeImpl();
        assertThat(sampleRuntime.config).isNull();
        sampleRuntime.initApplication(application);
        assertThat(sampleRuntime.config).isNotNull();
    }

    @Test
    void addModels() {
        SampleRuntimeImpl sampleRuntime = new SampleRuntimeImpl(application);
        assertThat(sampleRuntime.rawContent).isEmpty();
        sampleRuntime.addModels(Collections.singletonMap("name", "content"));
        assertThat(sampleRuntime.rawContent).hasSize(1);
    }

    @Test
    void getModel() {
        SampleRuntimeImpl sampleRuntime = new SampleRuntimeImpl(application);
        sampleRuntime.addModels(Collections.singletonMap("name", "content"));

        assertThat(sampleRuntime.getModel("name"))
                .isNotNull();

        assertThatThrownBy(() -> sampleRuntime.getModel("notExisting"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
