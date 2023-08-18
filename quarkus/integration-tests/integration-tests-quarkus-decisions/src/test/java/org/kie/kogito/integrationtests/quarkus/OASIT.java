package org.kie.kogito.integrationtests.quarkus;

import java.net.URL;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.RestAssured;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusIntegrationTest
class OASIT {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @TestHTTPResource("/")
    URL rootUrl;

    @Test
    public void testOASisValid() {
        String url = rootUrl.toString() + "/q/openapi"; // default location since Quarkus v1.10
        ParseOptions parseOptions = new ParseOptions();
        parseOptions.setResolve(true);
        SwaggerParseResult result = new OpenAPIV3Parser().readLocation(url, null, parseOptions);

        assertThat(result.getMessages()).isEmpty();

        OpenAPI openAPI = result.getOpenAPI();
        PathItem p1 = openAPI.getPaths().get("/basicAdd");
        assertThat(p1).isNotNull();
        assertThat(p1.getGet()).isNotNull();
        assertThat(p1.getPost()).isNotNull();
        PathItem p2 = openAPI.getPaths().get("/basicAdd/dmnresult");
        assertThat(p2).isNotNull();
        assertThat(p2.getPost()).isNotNull(); // only POST for ../dmnresult expected.
    }

    @Test
    public void testOASisSwaggerUICompatible() {
        String url = rootUrl.toString() + "/q/openapi"; // default location since Quarkus v1.10
        ParseOptions parseOptions = new ParseOptions();
        parseOptions.setResolve(false); // we want the actual OAS file (something ~like http://localhost:8080/q/openapi) as read by the Swagger UI on fetch.
        SwaggerParseResult result = new OpenAPIV3Parser().readLocation(url, null, parseOptions);
        assertThat(result.getMessages()).isEmpty();

        final String DMN_MODEL_NAME = "basicAdd";

        OpenAPI openAPI = result.getOpenAPI();
        PathItem p1 = openAPI.getPaths().get("/" + DMN_MODEL_NAME);
        assertThat(p1).isNotNull();
        assertThat(p1.getPost().getRequestBody().getContent().get("application/json").getSchema().get$ref()).startsWith("/dmnDefinitions.json#");
        assertThat(p1.getPost().getResponses().getDefault().getContent().get("application/json").getSchema().get$ref()).startsWith("/dmnDefinitions.json#");
    }

    @Test
    public void testOASdmnDefinitions() {
        RestAssured.given()
                .get("/dmnDefinitions.json")
                .then()
                .statusCode(200)
                .body("definitions", aMapWithSize(greaterThan(0)));
    }
}
