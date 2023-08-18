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

public class MessageStartEventIT extends AbstractCodegenIT {

    @Test
    public void testMessageStartEventProcess() throws Exception {

        Application app = generateCodeProcessesOnly("messagestartevent/MessageStartEvent.bpmn2");
        assertThat(app).isNotNull();

        Process<? extends Model> p = app.get(Processes.class).processById("MessageStartEvent");

        Model m = p.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customerId", "CUS-00998877");
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = p.createInstance(m);
        processInstance.start("customers", null);

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_COMPLETED);
        Model result = (Model) processInstance.variables();
        assertThat(result.toMap()).hasSize(1).containsKeys("customerId");
        assertThat(result.toMap().get("customerId")).isNotNull().isEqualTo("CUS-00998877");
    }

    @Test
    public void testMessageStartAndEndEventProcess() throws Exception {

        Application app = generateCodeProcessesOnly("messagestartevent/MessageStartAndEndEvent.bpmn2");
        assertThat(app).isNotNull();

        Process<? extends Model> p = app.get(Processes.class).processById("MessageStartEvent");

        Model m = p.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customerId", "CUS-00998877");
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = p.createInstance(m);
        processInstance.start("customers", null);

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_COMPLETED);
        Model result = (Model) processInstance.variables();
        assertThat(result.toMap()).hasSize(1).containsKeys("customerId");
        assertThat(result.toMap().get("customerId")).isNotNull().isEqualTo("CUS-00998877");
    }

    @Test
    public void testNoneAndMessageStartEventProcess() throws Exception {

        Application app = generateCodeProcessesOnly("messagestartevent/NoneAndMessageStartEvent.bpmn2");
        assertThat(app).isNotNull();

        Process<? extends Model> p = app.get(Processes.class).processById("MessageStartEvent");

        Model m = p.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customerId", "CUS-00998877");
        m.fromMap(parameters);
        // first start it via none start event
        ProcessInstance<?> processInstance = p.createInstance(m);
        processInstance.start();

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_COMPLETED);
        Model result = (Model) processInstance.variables();
        assertThat(result.toMap()).hasSize(2).containsKeys("customerId", "path");
        assertThat(result.toMap().get("customerId")).isNotNull().isEqualTo("CUS-00998877");
        assertThat(result.toMap().get("path")).isNotNull().isEqualTo("none");

        // next start it via message start event
        processInstance = p.createInstance(m);
        processInstance.start("customers", null);

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_COMPLETED);
        result = (Model) processInstance.variables();
        assertThat(result.toMap()).hasSize(2).containsKeys("customerId", "path");
        assertThat(result.toMap().get("customerId")).isNotNull().isEqualTo("CUS-00998877");
        assertThat(result.toMap().get("path")).isNotNull().isEqualTo("message");
    }
}
