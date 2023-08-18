package org.kie.kogito.quarkus.workflows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.kie.kogito.test.quarkus.kafka.KafkaTestClient;

import io.restassured.path.json.JsonPath;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkflowTestUtils {

    public static final int TIME_OUT_SECONDS = 50;
    public static final String KOGITO_PROCESSINSTANCES_EVENTS = "kogito-processinstances-events";

    private WorkflowTestUtils() {
    }

    public static JsonPath waitForKogitoProcessInstanceEvent(KafkaTestClient kafkaClient, boolean shutdownAfterConsume) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference<String> cloudEvent = new AtomicReference<>();

        kafkaClient.consume(KOGITO_PROCESSINSTANCES_EVENTS, rawCloudEvent -> {
            cloudEvent.set(rawCloudEvent);
            countDownLatch.countDown();
        });
        // give some time to consume the event
        assertThat(countDownLatch.await(TIME_OUT_SECONDS, TimeUnit.SECONDS)).isTrue();
        if (shutdownAfterConsume) {
            kafkaClient.shutdown();
        }
        return new JsonPath(cloudEvent.get());
    }

}
