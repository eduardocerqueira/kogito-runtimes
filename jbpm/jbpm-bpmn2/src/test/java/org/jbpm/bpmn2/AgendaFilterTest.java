package org.jbpm.bpmn2;

import org.jbpm.bpmn2.objects.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AgendaFilterTest extends JbpmBpmn2TestCase {

    @Test
    public void testNoFilter() throws Exception {
        kruntime = createKogitoProcessRuntime("BPMN2-AgendaFilter.bpmn2", "BPMN2-AgendaFilter.drl");

        Order order = new Order();
        order.setId("ORDER-1");
        kruntime.getKieSession().insert(order);

        kruntime.startProcess("Ruleflow");

        assertThat(order.isValid()).isTrue();
    }

    @Test
    public void testWithFilter() throws Exception {
        kruntime = createKogitoProcessRuntime("BPMN2-AgendaFilter.bpmn2", "BPMN2-AgendaFilter.drl");

        Order order = new Order();
        order.setId("ORDER-1");
        kruntime.getKieSession().insert(order);

        kruntime.startProcess("Ruleflow", match -> false);

        assertThat(order.isValid()).isFalse();
    }
}
