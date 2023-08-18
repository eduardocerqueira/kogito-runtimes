package org.kie.kogito.addons.quarkus.knative.serving.customfunctions;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.kie.kogito.addons.quarkus.knative.serving.customfunctions.Operation.CLOUD_EVENT_PARAMETER_NAME;
import static org.kie.kogito.addons.quarkus.knative.serving.customfunctions.Operation.PATH_PARAMETER_NAME;

class OperationTest {

    public static Stream<Arguments> parseSource() {
        return Stream.of(
                Arguments.of("service", Operation.builder().withService("service").build()),

                Arguments.of("service?", Operation.builder().withService("service").build()),

                Arguments.of("service?" + PATH_PARAMETER_NAME + "=/my_path", Operation.builder().withService("service").withPath("/my_path").build()),

                Arguments.of("service?" + CLOUD_EVENT_PARAMETER_NAME + "=true", Operation.builder().withService("service").withIsCloudEvent(true).build()),

                Arguments.of("service?" + PATH_PARAMETER_NAME + "=/my_path&" + CLOUD_EVENT_PARAMETER_NAME + "=true",
                        Operation.builder().withService("service").withPath("/my_path").withIsCloudEvent(true).build()));
    }

    @ParameterizedTest
    @MethodSource("parseSource")
    void parse(String operationValue, Operation expectedOperation) {
        assertThat(Operation.parse(operationValue)).isEqualTo(expectedOperation);
    }
}