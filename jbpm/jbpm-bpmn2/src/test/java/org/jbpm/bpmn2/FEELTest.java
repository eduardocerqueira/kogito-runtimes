package org.jbpm.bpmn2;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FEELTest extends JbpmBpmn2TestCase {

    @Test
    public void testGatewayFEEL() throws Exception {
        kruntime = createKogitoProcessRuntime("BPMN2-GatewayFEEL.bpmn2");

        Map<String, Object> params1 = new HashMap<String, Object>();
        params1.put("VA", Boolean.TRUE);
        params1.put("VB", Boolean.FALSE);
        org.jbpm.workflow.instance.WorkflowProcessInstance procInstance1 = (org.jbpm.workflow.instance.WorkflowProcessInstance) kruntime.startProcess("BPMN2-GatewayFEEL", params1);
        assertThat(procInstance1.getVariable("Task1")).isEqualTo("ok");
        assertThat(procInstance1.getVariable("Task2")).isEqualTo("ok");
        assertThat(procInstance1.getVariable("Task3")).isNull();
        assertNodeTriggered(procInstance1.getStringId(), "Task2", "VA and not(VB)");

        Map<String, Object> params2 = new HashMap<String, Object>();
        params2.put("VA", Boolean.FALSE);
        params2.put("VB", Boolean.TRUE);
        org.jbpm.workflow.instance.WorkflowProcessInstance procInstance2 = (org.jbpm.workflow.instance.WorkflowProcessInstance) kruntime.startProcess("BPMN2-GatewayFEEL", params2);
        assertThat(procInstance2.getVariable("Task1")).isEqualTo("ok");
        assertThat(procInstance2.getVariable("Task2")).isNull();
        assertThat(procInstance2.getVariable("Task3")).isEqualTo("ok");
        assertNodeTriggered(procInstance2.getStringId(), "Task3", "VB or not(VA)");
    }

    @Test
    public void testGatewayFEELWrong() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> createKogitoProcessRuntime("BPMN2-GatewayFEEL-wrong.bpmn2"))
                .withMessageContaining("Invalid FEEL expression: 'VA and Not(VB)'")
                .withMessageContaining("Invalid FEEL expression: 'VB or nOt(VA)'");
    }

}
