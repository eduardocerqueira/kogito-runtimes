package org.kie.kogito.grafana.model.functions;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BiGrafanaOperationTest {

    @Test
    public void testBiGrafanaOperation() {
        // Arrange
        GrafanaFunction firstBaseExpression = new BaseExpression("prefix1", "suffix1");
        GrafanaFunction secondBaseExpression = new BaseExpression("prefix2", "suffix2");
        GrafanaFunction biGrafanaOperation = new BiGrafanaOperation(GrafanaOperation.DIVISION, firstBaseExpression, secondBaseExpression);

        List<Label> labels = Collections.singletonList(new Label("key", "value"));

        // Act
        String result = biGrafanaOperation.render("body", labels);

        // Assert
        assertThat(result).isEqualTo("(prefix1_body_suffix1{key=value})/(prefix2_body_suffix2{key=value})");
    }
}
