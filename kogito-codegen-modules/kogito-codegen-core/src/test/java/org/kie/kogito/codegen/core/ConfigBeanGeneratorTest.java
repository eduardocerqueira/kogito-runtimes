package org.kie.kogito.codegen.core;

import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.QuarkusKogitoBuildContext;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigBeanGeneratorTest {

    @ParameterizedTest
    @MethodSource("org.kie.kogito.codegen.api.utils.KogitoContextTestUtils#contextBuilders")
    public void generate(KogitoBuildContext.Builder contextBuilder) {
        if (!QuarkusKogitoBuildContext.CONTEXT_NAME.equals(contextBuilder.build().name())) {
            // null GAV
            assertThat(contextBuilder.build().getGAV()).isEmpty();
            Optional<MethodCallExpr> setGavNull = getGavMethodCallExpr(contextBuilder);
            setGavNull.ifPresent(methodCallExpr -> assertThat(methodCallExpr.toString()).contains("null"));

            // with GAV
            KogitoGAV kogitoGAV = new KogitoGAV("groupId", "artifactId", "version");
            contextBuilder.withGAV(kogitoGAV);
            Optional<MethodCallExpr> setGav = getGavMethodCallExpr(contextBuilder);
            setGav.ifPresent(methodCallExpr -> assertThat(methodCallExpr.toString())
                    .contains(kogitoGAV.getGroupId())
                    .contains(kogitoGAV.getArtifactId())
                    .contains(kogitoGAV.getVersion()));
        }
    }

    private Optional<MethodCallExpr> getGavMethodCallExpr(KogitoBuildContext.Builder contextBuilder) {
        ConfigBeanGenerator configBeanGenerator = new ConfigBeanGenerator(contextBuilder.build());
        CompilationUnit compilationUnit = configBeanGenerator.toCompilationUnit();
        assertThat(compilationUnit.toString()).doesNotContain(configBeanGenerator.GAV_TEMPLATE);
        Optional<MethodCallExpr> setGav = compilationUnit.findFirst(MethodCallExpr.class, mc -> "setGav".equals(mc.getNameAsString()));
        boolean shouldMethodPresent = contextBuilder.build().hasDI();
        assertThat(setGav.isPresent()).isEqualTo(shouldMethodPresent);
        return setGav;
    }
}
