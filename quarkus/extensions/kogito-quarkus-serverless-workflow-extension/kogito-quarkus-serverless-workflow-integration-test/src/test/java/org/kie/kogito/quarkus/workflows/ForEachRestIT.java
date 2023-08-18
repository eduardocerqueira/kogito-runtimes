package org.kie.kogito.quarkus.workflows;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

@QuarkusTestResource(DivisionMockService.class)
@QuarkusIntegrationTest
class ForEachRestIT {

    @ParameterizedTest
    @ValueSource(strings = { "/forEachCustomType", "/forEachRest" })
    void testForEachWorkItem(String id) {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"input\" : " + Arrays.toString(DivisionMockService.dividends) + ", \"divisor\": " + DivisionMockService.divisor + "}}").when()
                .post(id)
                .then()
                .statusCode(201)
                .body("workflowdata.output", is(Arrays.stream(DivisionMockService.dividends).map(i -> i / DivisionMockService.divisor + 1).boxed().collect(Collectors.toList())))
                .body("workflowdata.response", nullValue());
    }

    @Test
    void testForEachSubflow() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"numbers\" : [1,2,3,4,5], \"constant\": 2}}").when()
                .post("/foreach_parent")
                .then()
                .statusCode(201)
                .body("workflowdata.products", is(Arrays.asList(2, 4, 6, 8, 10)));
    }
}
