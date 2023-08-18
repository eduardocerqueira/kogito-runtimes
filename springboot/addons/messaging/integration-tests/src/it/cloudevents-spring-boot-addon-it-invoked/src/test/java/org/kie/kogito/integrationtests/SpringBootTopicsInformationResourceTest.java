package org.kie.kogito.integrationtests;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kie.kogito.app.KogitoSpringbootApplication;
import org.kie.kogito.event.ChannelType;
import org.kie.kogito.event.Topic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
public class SpringBootTopicsInformationResourceTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void test() {
        RestAssured.port = randomServerPort;
        List<Topic> topicList = given()
                .when()
                .get("/messaging/topics")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath().getList(".", Topic.class);

        assertThat(topicList).hasSize(2).containsExactly(
                new Topic("cloudevents-addon-it-requests", ChannelType.INCOMING),
                new Topic("cloudevents-addon-it-responses", ChannelType.OUTGOING));
    }
}
