package org.kie.kogito.event.impl;

import org.junit.jupiter.api.Test;
import org.kie.kogito.event.process.MilestoneEventBody;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcessInstance;
import org.kie.kogito.process.flexible.ItemDescription.Status;
import org.kie.kogito.process.flexible.Milestone;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.event.process.ProcessInstanceEventBody.PROCESS_ID_META_DATA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessInstanceEventBatchTest {

    @Test
    public void testNoServiceDefined() {
        ProcessInstanceEventBatch batch = new ProcessInstanceEventBatch("", null);

        assertThat(batch.extractRuntimeSource(singletonMap(PROCESS_ID_META_DATA, "travels"))).isEqualTo("/travels");
        assertThat(batch.extractRuntimeSource(singletonMap(PROCESS_ID_META_DATA, "demo.orders"))).isEqualTo("/orders");
    }

    @Test
    public void testNoProcessIdDefined() {
        ProcessInstanceEventBatch batch = new ProcessInstanceEventBatch("http://localhost:8080", null);
        assertThat(batch.extractRuntimeSource(emptyMap())).isNull();
    }

    @Test
    public void testServiceDefined() {
        ProcessInstanceEventBatch batch = new ProcessInstanceEventBatch("http://localhost:8080", null);

        assertThat(batch.extractRuntimeSource(singletonMap(PROCESS_ID_META_DATA, "travels"))).isEqualTo("http://localhost:8080/travels");
        assertThat(batch.extractRuntimeSource(singletonMap(PROCESS_ID_META_DATA, "demo.orders"))).isEqualTo("http://localhost:8080/orders");
    }

    @Test
    public void testMilestones() {
        ProcessInstanceEventBatch batch = new ProcessInstanceEventBatch(null, null);

        KogitoWorkflowProcessInstance pi = mock(KogitoWorkflowProcessInstance.class);

        when(pi.milestones()).thenReturn(null);
        assertThat(batch.createMilestones(pi)).isEmpty();

        when(pi.milestones()).thenReturn(emptyList());
        assertThat(batch.createMilestones(pi)).isEmpty();

        Milestone milestone = Milestone.builder().withId("id").withName("name").withStatus(Status.AVAILABLE).build();
        when(pi.milestones()).thenReturn(singleton(milestone));

        MilestoneEventBody milestoneEventBody = MilestoneEventBody.create().id("id").name("name").status(Status.AVAILABLE.name()).build();
        assertThat(batch.createMilestones(pi)).containsOnly(milestoneEventBody);
    }
}
