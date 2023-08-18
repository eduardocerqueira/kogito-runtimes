package org.kie.kogito.grafana.model.functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseExpressionTest {

    @Test
    public void testBaseExpressionRender() {
        // Arrange
        BaseExpression baseExpression = new BaseExpression("prefix", "suffix");

        // Act
        String result = baseExpression.render("body", Collections.singletonList(new Label("test", "test")));

        // Assert
        assertThat(result).isEqualTo("prefix_body_suffix{test=test}");
    }

    @Test
    public void testBaseExpressionRenderWithMultipleLabels() {
        // Arrange
        GrafanaFunction baseExpression = new BaseExpression("prefix", "suffix");
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("first", "value"));
        labels.add(new Label("second", "\"value\""));

        // Act
        String result = baseExpression.render("body", labels);

        // Assert
        assertThat(result).isEqualTo("prefix_body_suffix{first=value,second=\"value\"}");
    }
}
