package org.jbpm.bpmn2;

import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceTest extends JbpmBpmn2TestCase {

    @Test
    public void testResourceType() throws Exception {
        kruntime = createKogitoProcessRuntime("BPMN2-MinimalProcess.bpmn2");
        kruntime.startProcess("Minimal");
    }

    @Test
    public void testMultipleProcessInOneFile() throws Exception {
        kruntime = createKogitoProcessRuntime("BPMN2-MultipleProcessInOneFile.bpmn2");
        KogitoProcessInstance processInstance = kruntime.startProcess("Evaluation");
        assertThat(processInstance).isNotNull();
        KogitoProcessInstance processInstance2 = kruntime.startProcess("Simple");
        assertThat(processInstance2).isNotNull();
    }

}
