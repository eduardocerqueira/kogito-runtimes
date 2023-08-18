package org.kie.kogito.process.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;
import org.kie.kogito.Model;
import org.kie.kogito.codegen.AbstractCodegenIT;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.Processes;
import org.kie.kogito.process.WorkItem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.process.impl.ProcessTestUtils.assertState;

class AdHocSubProcessIT extends AbstractCodegenIT {

    @Test
    void testActivationAdHoc() throws Exception {
        Application app = generateCodeProcessesOnly("cases/ActivationAdHoc.bpmn");
        assertThat(app).isNotNull();

        Process<? extends Model> p = app.get(Processes.class).processById("TestCase.ActivationAdHoc");
        Model model = p.createModel();
        Map<String, Object> params = new HashMap<>();
        params.put("favouriteColour", "yellow");
        model.fromMap(params);
        ProcessInstance<?> processInstance = p.createInstance(model);
        assertState(processInstance, ProcessInstance.STATE_PENDING);
        processInstance.start();

        assertState(processInstance, ProcessInstance.STATE_ACTIVE);

        List<WorkItem> workItems = processInstance.workItems();
        assertThat(workItems).hasSize(1);
        WorkItem workItem = workItems.get(0);
        params = new HashMap<>();
        params.put("favouriteColour", "blue");
        processInstance.completeWorkItem(workItem.getId(), params);

        assertState(processInstance, ProcessInstance.STATE_COMPLETED);
    }

    @Test
    void testCompletionAdHoc() throws Exception {
        Application app = generateCodeProcessesOnly("cases/CompletionAdHoc.bpmn");
        assertThat(app).isNotNull();

        Process<? extends Model> p = app.get(Processes.class).processById("TestCase.CompletionAdHoc");
        Model model = p.createModel();
        Map<String, Object> params = new HashMap<>();
        params.put("favouriteColour", "yellow");
        model.fromMap(params);
        ProcessInstance<?> processInstance = p.createInstance(model);
        assertState(processInstance, ProcessInstance.STATE_PENDING);
        processInstance.start();

        assertState(processInstance, ProcessInstance.STATE_ACTIVE);

        List<WorkItem> workItems = processInstance.workItems();
        assertThat(workItems).hasSize(1);
        WorkItem workItem = workItems.get(0);
        workItem.getParameters().put("favouriteColour", "green");
        params.put("favouriteColour", "green");
        processInstance.completeWorkItem(workItem.getId(), params);

        assertState(processInstance, ProcessInstance.STATE_COMPLETED);
    }
}
