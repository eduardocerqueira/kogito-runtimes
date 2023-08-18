package org.kie.kogito.test.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionHolderTest {

    private static final String RESOURCE_NAME = "my-test-resource";
    private static final String RESOURCE_PROPERTY = String.format(ConditionHolder.TEST_CATEGORY_PROPERTY, RESOURCE_NAME);

    private ConditionHolder condition;

    @BeforeEach
    public void setup() {
        condition = new ConditionHolder(RESOURCE_NAME);
    }

    @Test
    public void shouldBeEnabledByDefault() {
        assertThat(condition.isEnabled()).isTrue();
    }

    @Test
    public void shouldBeDisabledIfSystemPropertyDoesNotExist() {
        System.clearProperty(RESOURCE_PROPERTY);
        condition.enableConditional();
        assertThat(condition.isEnabled()).isFalse();
    }

    @Test
    public void shouldBeDisabledIfSystemPropertyIsNotTrue() {
        System.setProperty(RESOURCE_PROPERTY, "anything");
        condition.enableConditional();
        assertThat(condition.isEnabled()).isFalse();
    }

    @Test
    public void shouldBeEnabledIfSystemPropertyIsTrue() {
        System.setProperty(RESOURCE_PROPERTY, "true");
        condition.enableConditional();
        assertThat(condition.isEnabled()).isTrue();
    }
}
