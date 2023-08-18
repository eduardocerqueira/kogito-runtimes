package org.kie.kogito.codegen.sample.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SampleModelImplTest {

    SampleModelImpl sampleModel = new SampleModelImpl("name", "content", 2);

    @Test
    void execute() {
        assertThat(sampleModel.execute())
                .contains("-")
                .isEqualTo("content-content");
    }

    @Test
    void name() {
        assertThat(sampleModel.name()).isEqualTo("name");
    }
}