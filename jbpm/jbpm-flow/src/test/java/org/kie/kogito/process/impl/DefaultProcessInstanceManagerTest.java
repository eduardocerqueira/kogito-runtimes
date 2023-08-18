package org.kie.kogito.process.impl;

import java.util.UUID;

import org.jbpm.process.instance.impl.DefaultProcessInstanceManager;
import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultProcessInstanceManagerTest {

    @Test
    public void testCreateProcessInstance() {
        DefaultProcessInstanceManager pim = new DefaultProcessInstanceManager();
        final String processId = "processId";
        final String instanceId = UUID.randomUUID().toString();
        KogitoProcessInstance kpi = mock(KogitoProcessInstance.class);
        when(kpi.getProcessId()).thenReturn(processId);
        when(kpi.getStringId()).thenReturn(instanceId);

        pim.setLock(false);
        pim.internalAddProcessInstance(kpi);
        KogitoProcessInstance processInstance = pim.getProcessInstance(instanceId);
        assertThat(processInstance.getProcessId()).isEqualTo(processId);
        pim.removeProcessInstance(kpi);

        pim.setLock(true);
        pim.internalAddProcessInstance(kpi);
        processInstance = pim.getProcessInstance(instanceId, false);
        assertThat(processInstance.getProcessId()).isEqualTo(processId);
        processInstance = pim.getProcessInstance(instanceId, true);
        assertThat(processInstance.getProcessId()).isEqualTo(processId);
        pim.removeProcessInstance(kpi);
        pim.clearProcessInstances();
    }

}
