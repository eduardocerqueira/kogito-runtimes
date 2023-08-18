package org.kie.kogito.codegen.tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;
import org.kie.kogito.Model;
import org.kie.kogito.codegen.AbstractCodegenIT;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.Processes;

import static org.assertj.core.api.Assertions.assertThat;

public class VariableIT extends AbstractCodegenIT {

    @Test
    public void testVariablesWithReservedNameOnServiceTask() throws Exception {
        Application app = generateCodeProcessesOnly("servicetask/ServiceTaskWithReservedNameVariable.bpmn2");
        assertThat(app).isNotNull();

        Process<? extends Model> p = app.get(Processes.class).processById("test");

        Model m = p.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("package", "john");
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = p.createInstance(m);
        processInstance.start();

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_COMPLETED);
        Model result = (Model) processInstance.variables();
        assertThat(result.toMap()).hasSize(2).containsKeys("result");
        assertThat(result.toMap()).hasSize(2).containsKeys("package");
        assertThat(result.toMap().get("result")).isNotNull().isEqualTo("Hello Hello john!!");
    }
}