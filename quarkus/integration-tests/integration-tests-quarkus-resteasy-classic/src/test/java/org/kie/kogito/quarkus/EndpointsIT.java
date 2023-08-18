package org.kie.kogito.quarkus;

import java.net.URL;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@QuarkusIntegrationTest
public class EndpointsIT {

    @TestHTTPResource("/")
    URL rootUrl;

    @Test
    public void endpointTest() {
        String url = rootUrl.toString() + "/q/openapi";
        ParseOptions parseOptions = new ParseOptions();
        parseOptions.setResolve(true);
        SwaggerParseResult result = new OpenAPIV3Parser().readLocation(url, null, parseOptions);

        assertThat(result.getMessages()).isEmpty();

        OpenAPI openAPI = result.getOpenAPI();
        assertThat(openAPI.getPaths()).isNotEmpty();
    }

    @Test
    public void testGeneratedRestEndpoint() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body("{\"p\": {\"name\": \"Paul\"}}")
                .post("/dmnModel")
                .then()
                .statusCode(200)
                .body("d.Hello", not(emptyOrNullString()));
    }

    @Test
    public void testGeneratedResource() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/dmnDefinitions.json")
                .then()
                .statusCode(200)
                .body("definitions.tAddress.type", is("object"));

    }

}
