package org.kie.kogito.integrationtests;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.kie.kogito.event.cloudevents.CloudEventExtensionConstants;

import io.cloudevents.jackson.JsonFormat;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing our use cases on a controlled environment instead of using generated code.
 */
@QuarkusIntegrationTest
class QuarkusCloudEventResourceIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    // We are not using RestAssured to send a plain HTTP request mocking an arbitrary client sending a request without the properly headers.
    @Test
    void verifyHttpRequestNoMediaType() throws IOException {
        final URL url = new URL("http://localhost:" + RestAssured.port);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            con.setRequestProperty("ce-type", "myevent");
            con.setRequestProperty("ce-source", "/from/unit/test");
            con.setRequestProperty("ce-specversion", "1.0");
            con.setRequestProperty("ce-id", UUID.randomUUID().toString());
            con.setRequestProperty("ce-" + CloudEventExtensionConstants.PROCESS_REFERENCE_ID, "12345");
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.writeBytes("{ \"message\": \"Hola Mundo!\" }");
                out.flush();
            }
            assertThat(con.getResponseCode()).isEqualTo(200);
        } finally {
            con.disconnect();
        }
    }

    @Test
    void verifyHttpRequestNoMediaTypeCrazyContent() throws IOException {
        final URL url = new URL("http://localhost:" + RestAssured.port);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            con.setRequestProperty("ce-type", "myevent");
            con.setRequestProperty("ce-source", "/from/unit/test");
            con.setRequestProperty("ce-specversion", "1.0");
            con.setRequestProperty("ce-id", UUID.randomUUID().toString());
            con.setRequestProperty("ce-" + CloudEventExtensionConstants.PROCESS_REFERENCE_ID, "12345");
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                // the server will try to parse this as a JSON string...
                out.writeBytes("I'm just trying to hack this nonsense");
                out.flush();
            }
            assertThat(con.getResponseCode()).isEqualTo(400);
        } finally {
            con.disconnect();
        }
    }

    @Test
    void verifyHttpRequestWithJSONPayloadExpectsString() {
        final String source = "/from/unit/test";
        given().when()
                .body("{ \"message\": \"Hola Mundo!\" }")
                .header("ce-type", "myevent")
                .header("ce-source", source)
                .header("ce-specversion", "1.0")
                .header("ce-id", UUID.randomUUID().toString())
                .header("ce-" + CloudEventExtensionConstants.PROCESS_REFERENCE_ID, "12345")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("data.message", Matchers.equalTo("Hola Mundo!"))
                .body(CloudEventExtensionConstants.PROCESS_REFERENCE_ID, Matchers.equalTo("12345"));
    }

    @Test
    void verifyHttpRequestWithJSONPayloadExpectsPOJO() {
        final String source = "/from/unit/test";

        Message msg = given()
                .when()
                .body("{ \"message\": \"Hola Mundo!\" }")
                .header("ce-type", "myevent")
                .header("ce-source", source)
                .header("ce-specversion", "1.0")
                .header("ce-id", UUID.randomUUID().toString())
                .header("ce-" + CloudEventExtensionConstants.PROCESS_REFERENCE_ID, "12345")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .post("/")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("data.message", Matchers.equalTo("Hola Mundo!"))
                .body("source", Matchers.equalTo(source))
                .extract().body().jsonPath().getObject("data", Message.class);
        assertThat(msg.getMessage()).isEqualTo("Hola Mundo!");
    }

    @Test
    void verifyHttpRequestWithCEPayloadExpectsPOJO() {
        final String source = "/from/unit/test";
        given().when()
                .body("{\"" + CloudEventExtensionConstants.PROCESS_REFERENCE_ID
                        + "\":\"12345!\", \"data\":{\"message\":\"Hi World!\"},\"id\":\"x10\",\"source\":\"/from/unit/test\",\"specversion\":\"1.0\",\"type\":\"myevent\",\"datacontenttype\":\"application/json\"}")
                .contentType(MediaType.valueOf(JsonFormat.CONTENT_TYPE).withCharset(StandardCharsets.UTF_8.name()).toString())
                .post("/")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("source", Matchers.equalTo("/from/unit/test"))
                .body(CloudEventExtensionConstants.PROCESS_REFERENCE_ID, Matchers.equalTo("12345!"));
    }

    @Test
    void verifyHttpRequestWithCEPayloadExpectsString() {
        final String source = "/from/unit/test";
        final Message msg = given().when()
                .body("{\"data\":{\"message\":\"Hi World!\"},\"id\":\"x10\",\"source\":\"/from/unit/test\",\"specversion\":\"1.0\",\"type\":\"myevent\",\"datacontenttype\":\"application/json\"}")
                .contentType(JsonFormat.CONTENT_TYPE)
                .post("")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(Matchers.isA(String.class))
                .extract().body().jsonPath().getObject("data", Message.class);

        assertThat(msg).isNotNull();
        assertThat(msg.getMessage()).isEqualTo("Hi World!");
    }

    public static class Message {

        private String message;

        public Message() {
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
