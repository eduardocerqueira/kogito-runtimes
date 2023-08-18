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

        //Will be fixed by https://issues.redhat.com/browse/KOGITO-5734
        assertThat(result.getMessages()).containsExactly(
                "attribute components.schemas.MultiinstancetaskprocessModelInput.items is missing",
                "attribute components.schemas.MultiinstancetaskprocessModelInput.items is missing",
                "attribute components.schemas.MultiinstancetaskprocessModelOutput.items is missing",
                "attribute components.schemas.MultiinstancetaskprocessModelOutput.items is missing",
                "attribute components.schemas.Multiinstancetaskprocess_2_TaskInput.items is missing",
                "attribute components.schemas.Multiinstancetaskprocess_2_TaskInput.items is missing");

        OpenAPI openAPI = result.getOpenAPI();
        PathItem p1 = openAPI.getPaths().get("/approvals");
        assertThat(p1).isNotNull();
        assertThat(p1.getGet()).isNotNull();
        assertThat(p1.getPost()).isNotNull();
    }

}
