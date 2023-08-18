package org.kie.kogito.serverless.workflow.utils;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BuildEvaluatorTest {

    @BeforeEach
    void setup() {
        ConfigResolverHolder.setConfigResolver(new MapConfigResolver(Collections.singletonMap("key", "value")));
    }

    @Test
    void testSecretWithinExpression() {
        assertThat(BuildEvaluator.eval(ExpressionHandlerUtils.trimExpr("${ $SECRET.key}"))).isEqualTo("value");
    }

    @Test
    void testSecret() {
        assertThat(BuildEvaluator.eval("$SECRET.key")).isEqualTo("value");
    }

    @Test
    void testPlain() {
        assertThat(BuildEvaluator.eval("key")).isEqualTo("key");
    }
}
