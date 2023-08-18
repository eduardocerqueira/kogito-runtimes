package org.kie.kogito.integrationtests;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kie.kogito.event.ChannelType;
import org.kie.kogito.event.Topic;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusIntegrationTest
public class QuarkusTopicsInformationResourceIT {

    @Test
    public void test() {
        List<Topic> topicList = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/messaging/topics")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath().getList(".", Topic.class);

        assertThat(topicList).hasSize(2).containsOnly(
                new Topic("cloudevents-addon-it-requests", ChannelType.INCOMING),
                new Topic("cloudevents-addon-it-responses", ChannelType.OUTGOING));
    }
}
