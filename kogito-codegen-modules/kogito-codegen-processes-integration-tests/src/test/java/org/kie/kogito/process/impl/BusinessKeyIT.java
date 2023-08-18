package org.kie.kogito.process.impl;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;
import org.kie.kogito.Model;
import org.kie.kogito.codegen.AbstractCodegenIT;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.Processes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.process.impl.ProcessTestUtils.assertState;

class BusinessKeyIT extends AbstractCodegenIT {

    @Test
    void testBusinessKey() throws Exception {
        String businessKey = "some business key";
        Application app = generateCodeProcessesOnly("cases/ActivationAdHoc.bpmn");
        assertThat(app).isNotNull();

        Process<? extends Model> p = app.get(Processes.class).processById("TestCase.ActivationAdHoc");
        ProcessInstance<?> processInstance = p.createInstance(businessKey, p.createModel());
        assertState(processInstance, ProcessInstance.STATE_PENDING);
        assertThat(processInstance.businessKey()).isEqualTo(businessKey);
    }
}