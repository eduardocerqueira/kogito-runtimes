package org.kie.kogito.mongodb;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

import org.bson.Document;
import org.drools.io.ClassPathResource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.bpmn2.BpmnProcess;
import org.kie.kogito.process.bpmn2.BpmnVariables;
import org.kie.kogito.serialization.process.MarshallerContextName;
import org.kie.kogito.serialization.process.ProcessInstanceMarshallerService;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

class DocumentProcessInstanceMarshallerTest {

    ProcessInstanceMarshallerService marshaller =
            ProcessInstanceMarshallerService.newBuilder().withDefaultObjectMarshallerStrategies()
                    .withContextEntries(singletonMap(MarshallerContextName.MARSHALLER_FORMAT, MarshallerContextName.MARSHALLER_FORMAT_JSON)).build();

    static BpmnProcess process;
    static Document doc;

    @BeforeAll
    static void setup() throws URISyntaxException, IOException {
        process = BpmnProcess.from(new ClassPathResource("BPMN2-UserTask.bpmn2")).get(0);
        process.configure();

    }

    @Test
    void testMarshalProcessInstance() {
        ProcessInstance<BpmnVariables> processInstance = process.createInstance(BpmnVariables.create(singletonMap("test", "testValue")));
        processInstance.start();
        doc = Document.parse(new String(marshaller.marshallProcessInstance(processInstance)));
        assertThat(doc).as("Marshalled value should not be null").isNotNull()
                .containsEntry("id", processInstance.id())
                .containsEntry("description", processInstance.description());
        assertThat(doc.get("context", Document.class).getList("variable", Document.class)).hasSize(1);
        assertThat(doc.get("context", Document.class).getList("variable", Document.class).get(0)).containsEntry("name", "test");
        assertThat(doc.get("context", Document.class).getList("variable", Document.class).get(0).get("value", Document.class)).containsEntry("value", "testValue");
    }

    @Test
    void testUnmarshalProcessInstance() {
        ProcessInstance<BpmnVariables> processInstance = (ProcessInstance<BpmnVariables>) marshaller.unmarshallProcessInstance(doc.toJson().getBytes(), process);
        assertThat(processInstance).as("Unmarshalled value should not be null").isNotNull();
        assertThat(processInstance.id()).isEqualTo(doc.get("id"));
        assertThat(processInstance.description()).isEqualTo(doc.get("description"))
                .isEqualTo("User Task");
        BpmnVariables variables = processInstance.variables();
        String testVar = (String) variables.get("test");
        assertThat(testVar).isEqualTo("testValue");
    }

    @Test
    void testProcessInstanceReadOnly() {
        ProcessInstance<BpmnVariables> processInstance = process.createInstance(BpmnVariables.create(Collections.singletonMap("test", "testValue")));
        processInstance.start();
        doc = Document.parse(new String(marshaller.marshallProcessInstance(processInstance)));
        ProcessInstance<BpmnVariables> processInstanceReadOnly = (ProcessInstance<BpmnVariables>) marshaller.unmarshallProcessInstance(doc.toJson().getBytes(), process);
        assertThat(processInstanceReadOnly).as("Unmarshalled value should not be null").isNotNull();
        ProcessInstance<BpmnVariables> pi = (ProcessInstance<BpmnVariables>) marshaller.unmarshallReadOnlyProcessInstance(doc.toJson().getBytes(), process);
        assertThat(pi).as("Unmarshalled value should not be null").isNotNull();
    }

}
