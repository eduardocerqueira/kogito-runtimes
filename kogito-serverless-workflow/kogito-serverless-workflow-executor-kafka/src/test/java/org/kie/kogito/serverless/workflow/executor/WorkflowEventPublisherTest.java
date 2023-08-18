package org.kie.kogito.serverless.workflow.executor;

import org.junit.jupiter.api.Test;
import org.kie.kogito.event.DataEvent;
import org.kie.kogito.event.DataEventFactory;

import com.fasterxml.jackson.databind.JsonNode;

import io.cloudevents.CloudEvent;
import io.cloudevents.jackson.JsonCloudEventData;
import io.serverlessworkflow.api.Workflow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kie.kogito.serverless.workflow.fluent.ActionBuilder.trigger;
import static org.kie.kogito.serverless.workflow.fluent.EventDefBuilder.eventDef;
import static org.kie.kogito.serverless.workflow.fluent.StateBuilder.operation;
import static org.kie.kogito.serverless.workflow.fluent.WorkflowBuilder.jsonObject;
import static org.kie.kogito.serverless.workflow.fluent.WorkflowBuilder.workflow;

public class WorkflowEventPublisherTest {

    @Test
    void testPublisher() {
        JsonNode expected = jsonObject().put("name", "Javierito");
        Workflow workflow = workflow("testPublishEvent").start(operation().action(trigger(eventDef("testPublish"), "{name: .name}"))).end().build();
        try (StaticWorkflowApplication application = StaticWorkflowApplication.create()) {
            assertThat(MockKafkaEventEmitterFactory.producer.history()).isEmpty();
            application.execute(workflow, expected);
            assertThat(MockKafkaEventEmitterFactory.producer.history()).hasSize(1);
            CloudEvent cloudEvent = MockKafkaEventEmitterFactory.producer.history().get(0).value();
            assertThat(cloudEvent.getData()).isInstanceOf(JsonCloudEventData.class);
            DataEvent<JsonNode> dataEvent = DataEventFactory.from(cloudEvent, data -> ((JsonCloudEventData) data).getNode());
            assertThat(dataEvent.getData()).isInstanceOf(JsonNode.class);
            JsonNode jsonNode = dataEvent.getData();
            assertThat(jsonNode).isEqualTo(expected);
        }
    }
}
