package org.kie.kogito.codegen.sample.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SampleConfigImplTest {

    @Test
    void numberOfCopy() {
        assertThat(new SampleConfigImpl().numberOfCopy()).isEqualTo(1);
        assertThat(new SampleConfigImpl(10).numberOfCopy()).isEqualTo(10);

        SampleConfigImpl sampleConfig = new SampleConfigImpl();
        sampleConfig.setNumberOfCopy(10);
        assertThat(sampleConfig.numberOfCopy()).isEqualTo(10);
    }
}