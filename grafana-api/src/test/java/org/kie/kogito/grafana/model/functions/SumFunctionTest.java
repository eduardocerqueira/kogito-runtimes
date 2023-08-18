package org.kie.kogito.grafana.model.functions;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumFunctionTest {

    @Test
    public void testSumFunction() {
        // Arrange
        BaseExpression baseExpression = new BaseExpression("prefix", "suffix");
        SumFunction sumFunction = new SumFunction(baseExpression);

        List<Label> labels = Collections.singletonList(new Label("key", "value"));

        // Act
        String result = sumFunction.render("body", labels);

        // Assert
        assertThat(result).isEqualTo("sum(prefix_body_suffix{key=value})");
    }
}
