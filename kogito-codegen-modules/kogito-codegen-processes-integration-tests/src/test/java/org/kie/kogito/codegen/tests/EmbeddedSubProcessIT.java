package org.kie.kogito.codegen.tests;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;
import org.kie.kogito.Model;
import org.kie.kogito.codegen.AbstractCodegenIT;
import org.kie.kogito.codegen.data.Person;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.Processes;
import org.kie.kogito.process.WorkItem;

import static org.assertj.core.api.Assertions.assertThat;

public class EmbeddedSubProcessIT extends AbstractCodegenIT {

    @Test
    public void testEmbeddedSubProcess() throws Exception {

        Application app = generateCodeProcessesOnly("subprocess/EmbeddedSubProcess.bpmn2");
        assertThat(app).isNotNull();

        Process<? extends Model> p = app.get(Processes.class).processById("SubProcess");

        Model m = p.createModel();
        Map<String, Object> parameters = new HashMap<>();
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = p.createInstance(m);
        processInstance.start();

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_COMPLETED);
    }

    @Test
    public void testEmbeddedSubProcessWithUserTaskAndVariableScopes() throws Exception {

        Application app = generateCodeProcessesOnly("subprocess/EmbeddedSubProcessWithUserTask.bpmn2");
        assertThat(app).isNotNull();

        Process<? extends Model> p = app.get(Processes.class).processById("embeddedWithUserTask");
        Person person = new Person("john", 25);
        Model m = p.createModel();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("person", person);
        m.fromMap(parameters);

        ProcessInstance<?> processInstance = p.createInstance(m);
        processInstance.start();

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_ACTIVE);

        List<WorkItem> tasks = processInstance.workItems();
        assertThat(tasks).hasSize(1);

        WorkItem wi = processInstance.workItem(tasks.get(0).getId());
        assertThat(wi).isNotNull();

        assertThat(wi.getParameters()).containsKey("person");
        assertThat(wi.getParameters()).extracting("person").isEqualTo(person);

        processInstance.completeWorkItem(tasks.get(0).getId(), new HashMap<>(Collections.singletonMap("person", new Person("mary", 20))));

        assertThat(processInstance.status()).isEqualTo(ProcessInstance.STATE_COMPLETED);
    }
}
