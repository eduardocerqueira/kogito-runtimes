package org.kie.kogito.codegen.tests;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;
import org.kie.kogito.Model;
import org.kie.kogito.codegen.AbstractCodegenIT;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.Processes;
import org.kie.kogito.process.impl.Sig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.kie.kogito.test.utils.ProcessInstancesTestUtils.assertOne;
import static org.kie.kogito.test.utils.ProcessInstancesTestUtils.getFirst;

public class SubProcessIT extends AbstractCodegenIT {

    @Test
    public void testSubProcess() throws Exception {
        Application app = generateCodeProcessesOnly("subprocess/SubProcess.bpmn", "subprocess/ParentProcess.bpmn");
        assertThat(app).isNotNull();

        Process<? extends Model> parent = app.get(Processes.class).processById("parent");
        Process<? extends Model> subProcess = app.get(Processes.class).processById("subprocess");

        Model m = parent.createModel();
        m.fromMap(Collections.singletonMap("name", "test"));

        ProcessInstance<? extends Model> processInstance = parent.createInstance(m);
        processInstance.start();

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_ACTIVE);

        assertOne(subProcess.instances());

        ProcessInstance<? extends Model> subProcessInstance = getFirst(subProcess.instances());
        assertThat(subProcessInstance.variables().toMap()).hasSize(3).contains(
                entry("constant", "aString"), entry("name", "test"), entry("review", null));

        subProcessInstance.send(Sig.of("end", "another review"));
        assertThat(subProcessInstance.status()).isEqualTo(ProcessInstance.STATE_COMPLETED);

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_ACTIVE);

        processInstance.send(Sig.of("end", null));

        assertThat(processInstance.variables().toMap()).hasSize(2).contains(
                entry("name", "test"), entry("review", "another review"));

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_COMPLETED);
    }
}
