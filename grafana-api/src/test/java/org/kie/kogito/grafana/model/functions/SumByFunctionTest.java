package org.kie.kogito.grafana.model.functions;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumByFunctionTest {

    @Test
    public void testSumByFunction() {
        // Arrange
        BaseExpression baseExpression = new BaseExpression("prefix", "suffix");
        GrafanaFunction sumFunction = new SumByFunction(baseExpression, "id");

        List<Label> labels = Collections.singletonList(new Label("id", "value"));

        // Act
        String result = sumFunction.render("body", labels);

        // Assert
        assertThat(result).isEqualTo("sum by (id) (prefix_body_suffix{id=value})");
    }
}
