package org.jbpm.bpmn2;

import java.util.Collections;

import org.jbpm.process.instance.impl.demo.DoNothingWorkItemHandler;
import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CollaborationTest extends JbpmBpmn2TestCase {

    @Test
    public void testBoundaryMessageCollaboration() throws Exception {
        kruntime = createKogitoProcessRuntime("collaboration/Collaboration-BoundaryMessage.bpmn2");
        kruntime.getKogitoWorkItemManager().registerWorkItemHandler("Human Task", new DoNothingWorkItemHandler());
        KogitoProcessInstance pid = kruntime.startProcess("collaboration.BoundaryMessage", Collections.singletonMap("MessageId", "2"));
        kruntime.signalEvent("Message-collaboration", new Message("1", "example"), pid.getStringId());
        assertProcessInstanceActive(pid);
        kruntime.signalEvent("Message-collaboration", new Message("2", "example"), pid.getStringId());
        assertProcessInstanceCompleted(pid);
    }

    @Test
    public void testStartMessageCollaboration() throws Exception {
        kruntime = createKogitoProcessRuntime("collaboration/Collaboration-StartMessage.bpmn2");
        kruntime.signalEvent("Message-collaboration", new Message("1", "example"));
        assertThat(getNumberOfProcessInstances("collaboration.StartMessage")).isEqualTo(1);
    }

    @Test
    public void testStartMessageCollaborationNoMatch() throws Exception {
        kruntime = createKogitoProcessRuntime("collaboration/Collaboration-StartMessage.bpmn2");

        kruntime.signalEvent("Message-collaboration", new Message("2", "example"));
        assertThat(getNumberOfProcessInstances("collaboration.StartMessage")).isZero();
    }

    @Test
    public void testIntermediateMessageCollaboration() throws Exception {
        kruntime = createKogitoProcessRuntime("collaboration/Collaboration-IntermediateMessage.bpmn2");
        KogitoProcessInstance pid = kruntime.startProcess("collaboration.IntermediateMessage", Collections.singletonMap("MessageId", "2"));
        kruntime.signalEvent("Message-collaboration", new Message("1", "example"), pid.getStringId());
        assertProcessInstanceActive(pid);
        kruntime.signalEvent("Message-collaboration", new Message("2", "example"), pid.getStringId());
        assertProcessInstanceCompleted(pid);
    }

    @Test
    public void testInvalidIntermediateMessageCollaboration() throws Exception {
        kruntime = createKogitoProcessRuntime("collaboration/Collaboration-IntermediateMessage.bpmn2");

        KogitoProcessInstance pid = kruntime.startProcess("collaboration.IntermediateMessage", Collections.singletonMap("MessageId", "2"));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            kruntime.signalEvent("Message-collaboration", new Message(null, "example"), pid.getStringId());
        });

        assertProcessInstanceActive(pid);
    }
}
