package org.kie.kogito.serverless.workflow.utils;

import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.serverless.workflow.test.MockBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.Mockito.when;

public class KogitoProcessContextResolverTest {

    KogitoProcessContext context = MockBuilder.kogitoProcessContext()
            .withProcessInstanceMock(it -> {
                when(it.getId()).thenReturn("value-id");
                when(it.getProcessId()).thenReturn("value-process-id");
                when(it.getProcessName()).thenReturn("value-name");
            }).build();

    @Test
    void testGetInstanceId() {
        assertThat(KogitoProcessContextResolver.get().readKey(context, "instanceId")).isEqualTo("value-id");
    }

    @Test
    void testGetId() {
        assertThat(KogitoProcessContextResolver.get().readKey(context, "id")).isEqualTo("value-process-id");
    }

    @Test
    void testGetName() {
        assertThat(KogitoProcessContextResolver.get().readKey(context, "name")).isEqualTo("value-name");
    }

    @Test
    void testGetNonExistentKey() {
        assertThatIllegalArgumentException().isThrownBy(() -> KogitoProcessContextResolver.get().readKey(context, "nonexistent"));
    }
}
