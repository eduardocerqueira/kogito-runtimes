package org.kie.kogito.grafana.model.functions;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IncreaseFunctionTest {

    @Test
    public void testSumByFunction() {
        // Arrange
        BaseExpression baseExpression = new BaseExpression("prefix", "suffix");
        GrafanaFunction sumFunction = new IncreaseFunction(baseExpression, "1m");

        List<Label> labels = Collections.singletonList(new Label("key", "value"));

        // Act
        String result = sumFunction.render("body", labels);

        // Assert
        assertThat(result).isEqualTo("increase(prefix_body_suffix{key=value}[1m])");
    }
}
