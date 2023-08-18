package org.kie.kogito.integrationtests.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.config.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
@AutoConfigureMetrics
public class MonitoringTest extends BaseRestTest {

    @Autowired
    ConfigBean configBean;

    @Test
    public void test() {
        String pId = given().contentType(ContentType.JSON)
                .when()
                .post("/monitoring")
                .then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()))
                .extract().path("id");

        String response = given().contentType(ContentType.JSON)
                .when()
                .get("/actuator/prometheus")
                .then()
                .statusCode(200)
                .extract().body().asString();

        KogitoGAV kogitoGAV = configBean.getGav().get();

        assertThat(response).contains(format("kogito_process_instance_started_total{app_id=\"default-process-monitoring-listener\",artifactId=\"%s\",process_id=\"monitoring\",version=\"%s\",} 1.0",
                kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));
        assertThat(response).contains(format("kogito_process_instance_running_total{app_id=\"default-process-monitoring-listener\",artifactId=\"%s\",process_id=\"monitoring\",version=\"%s\",} 1.0",
                kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));

        String taskId = given()
                .contentType(ContentType.JSON)
                .queryParam("user", "admin")
                .queryParam("group", "managers")
                .pathParam("pId", pId)
                .when()
                .get("/monitoring/{pId}/tasks")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].id");

        given().contentType(ContentType.JSON)
                .pathParam("pId", pId)
                .pathParam("taskId", taskId)
                .queryParam("user", "admin")
                .queryParam("group", "managers")
                .body("{}")
                .when()
                .post("/monitoring/{pId}/MonitoringTask/{taskId}/phases/complete")
                .then()
                .statusCode(200);

        response = given().contentType(ContentType.JSON)
                .when()
                .get("/actuator/prometheus")
                .then()
                .statusCode(200)
                .extract().body().asString();

        assertThat(response).contains(format("kogito_work_item_duration_seconds_max{artifactId=\"%s\",name=\"MonitoringTask\",version=\"%s\",}", kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));
        assertThat(response).contains(format("kogito_work_item_duration_seconds_count{artifactId=\"%s\",name=\"MonitoringTask\",version=\"%s\",} 1.0", kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));
        assertThat(response).contains(format("kogito_work_item_duration_seconds_sum{artifactId=\"%s\",name=\"MonitoringTask\",version=\"%s\",}", kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));

        assertThat(response)
                .contains(format("kogito_process_instance_completed_total{app_id=\"default-process-monitoring-listener\",artifactId=\"%s\",node_name=\"2\",process_id=\"monitoring\",version=\"%s\",} 1.0",
                        kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));
        assertThat(response).contains(format("kogito_process_instance_running_total{app_id=\"default-process-monitoring-listener\",artifactId=\"%s\",process_id=\"monitoring\",version=\"%s\",} 0.0",
                kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));
        assertThat(response).contains(format("kogito_process_instance_duration_seconds_max{app_id=\"default-process-monitoring-listener\",artifactId=\"%s\",process_id=\"monitoring\",version=\"%s\",}",
                kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));
        assertThat(response)
                .contains(format("kogito_process_instance_duration_seconds_count{app_id=\"default-process-monitoring-listener\",artifactId=\"%s\",process_id=\"monitoring\",version=\"%s\",} 1.0",
                        kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));
        assertThat(response).contains(format("kogito_process_instance_duration_seconds_sum{app_id=\"default-process-monitoring-listener\",artifactId=\"%s\",process_id=\"monitoring\",version=\"%s\",}",
                kogitoGAV.getArtifactId(), kogitoGAV.getVersion()));
    }

}
