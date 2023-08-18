package org.kie.kogito.grafana.model.functions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LabelTest {

    @Test
    public void testLabelGetters() {
        // Arrange
        Label label = new Label("key", "value");

        // Act and Assert
        assertThat(label.getKey()).isEqualTo("key");
        assertThat(label.getValue()).isEqualTo("value");
    }

    @Test
    public void testLabelRender() {
        // Arrange
        Label label = new Label("key", "value");

        // Act and Assert
        assertThat(label.render()).isEqualTo("key=value");
    }
}
