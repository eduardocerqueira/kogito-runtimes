package org.jbpm.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.jbpm.process.instance.impl.humantask.HumanTaskWorkItemHandler;
import org.junit.jupiter.api.Test;
import org.kie.kogito.Config;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessConfig;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.ProcessInstanceReadMode;
import org.kie.kogito.process.ProcessInstances;
import org.kie.kogito.process.WorkItem;
import org.kie.kogito.process.WorkItemHandlerConfig;
import org.kie.kogito.process.workitem.Policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonSchemaUtilTest {

    private final static String example = "{\"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "    \"type\": \"object\",\n" +
            "    \"properties\": {\n" +
            "        \"traveller\": {\n" +
            "            \"type\": \"object\",\n" +
            "            \"properties\": {\n" +
            "                \"address\": {\n" +
            "                    \"type\": \"object\",\n" +
            "                    \"properties\": {\n" +
            "                        \"city\": {\n" +
            "                            \"type\": \"string\"\n" +
            "                        },\n" +
            "                        \"country\": {\n" +
            "                            \"type\": \"string\"\n" +
            "                        },\n" +
            "                        \"street\": {\n" +
            "                            \"type\": \"string\"\n" +
            "                        },\n" +
            "                        \"zipCode\": {\n" +
            "                            \"type\": \"string\"\n" +
            "                        }\n" +
            "                    }\n" +
            "                },\n" +
            "                \"email\": {\n" +
            "                    \"type\": \"string\"\n" +
            "                },\n" +
            "                \"firstName\": {\n" +
            "                    \"type\": \"string\"\n" +
            "                },\n" +
            "                \"lastName\": {\n" +
            "                    \"type\": \"string\"\n" +
            "                },\n" +
            "                \"nationality\": {\n" +
            "                    \"type\": \"string\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"input\": true\n" +
            "        },\n" +
            "        \"approved\": {\n" +
            "            \"type\": \"boolean\",\n" +
            "            \"output\": true\n" +
            "        }\n" +
            "    }}";

    @Test
    void testJsonSchema() throws IOException {
        InputStream in = new ByteArrayInputStream(example.getBytes());
        Map<String, Object> schemaMap = JsonSchemaUtil.load(in);
        in.close();
        assertThat(schemaMap).containsEntry("type", "object");

        Map<String, Object> properties = (Map<String, Object>) schemaMap.get("properties");
        assertThat(properties).hasSize(2);
        assertThat((Boolean) ((Map) properties.get("approved")).get("output")).isTrue();
        assertThat((Boolean) ((Map) properties.get("traveller")).get("input")).isTrue();
    }

    @Test
    <T> void testJsonSchemaPhases() throws IOException {
        InputStream in = new ByteArrayInputStream(example.getBytes());
        Policy<T>[] policies = new Policy[0];
        Map<String, Object> schemaMap = JsonSchemaUtil.load(in);
        in.close();
        Process<T> process = mock(Process.class);
        ProcessInstances<T> processInstances = mock(ProcessInstances.class);
        when(process.instances()).thenReturn(processInstances);
        ProcessInstance<T> processInstance = mock(ProcessInstance.class);
        when(processInstances.findById("pepe", ProcessInstanceReadMode.READ_ONLY)).thenReturn((Optional) Optional.of(processInstance));
        WorkItem task = mock(WorkItem.class);
        when(processInstance.workItem("task", policies)).thenReturn(task);
        when(task.getPhase()).thenReturn("active");
        Config config = mock(Config.class);
        ProcessConfig processConfig = mock(ProcessConfig.class);
        when(config.get(any())).thenReturn(processConfig);
        WorkItemHandlerConfig workItemHandlerConfig = mock(WorkItemHandlerConfig.class);
        when(processConfig.workItemHandlers()).thenReturn(workItemHandlerConfig);
        KogitoWorkItemHandler workItemHandler = new HumanTaskWorkItemHandler();
        when(workItemHandlerConfig.forName("Human Task")).thenReturn(workItemHandler);
        schemaMap = JsonSchemaUtil.addPhases(process, workItemHandler, "pepe", "task", policies, schemaMap);
        assertThat(((Collection) schemaMap.get("phases"))).isNotEmpty();
    }
}
