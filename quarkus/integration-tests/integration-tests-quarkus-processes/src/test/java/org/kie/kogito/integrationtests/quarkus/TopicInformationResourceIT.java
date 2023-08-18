package org.kie.kogito.integrationtests.quarkus;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kie.kogito.event.ChannelType;
import org.kie.kogito.event.EventKind;
import org.kie.kogito.event.Topic;
import org.kie.kogito.event.cloudevents.CloudEventMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.test.junit.QuarkusIntegrationTest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusIntegrationTest
public class TopicInformationResourceIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicInformationResourceIT.class);

    @Test
    void verifyTopicsInformation() {
        final List<Topic> topics = Arrays.asList(given().get("/messaging/topics").as(Topic[].class));
        LOGGER.info("Topics registered in the service are {}", topics);
        assertThat(topics).isNotEmpty();
        assertThat(topics.stream().anyMatch(
                t -> t.getType() == ChannelType.INCOMING &&
                        t.getName().equals("kogito_it_test") &&
                        t.getEventsMeta().contains(new CloudEventMeta("pong_end", "", EventKind.CONSUMED))))
                                .isTrue();
    }
}
