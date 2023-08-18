package org.kie.kogito.jobs.service.api.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EventUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "myExtension", "myëxtension" })
    void validateExtensionNameUnsuccessful(String name) {
        assertThatThrownBy(() -> EventUtils.validateExtensionName(name))
                .hasMessageStartingWith("Invalid attribute or extension name:");
    }

    @Test
    void validateExtensionNameNullUnsuccessful() {
        validateExtensionNameUnsuccessful(null);
    }

    @ParameterizedTest
    @ValueSource(strings = { "successful", "value1", "value3", "v" })
    void validateExtensionNameSuccessful(String name) {
        assertThatNoException().isThrownBy(() -> EventUtils.validateExtensionName(name));
    }
}
