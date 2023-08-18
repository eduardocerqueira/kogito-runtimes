package org.kie.kogito.codegen.decision;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Properties;
import java.util.function.Consumer;

import org.assertj.core.api.Assertions;
import org.drools.codegen.common.GeneratedFile;
import org.junit.jupiter.api.Test;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.JavaKogitoBuildContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DecisionValidationTest {

    private DecisionCodegen codeGenerator(String path, Consumer<Properties> codeGenContextProperties) throws Exception {
        Properties props = new Properties();
        codeGenContextProperties.accept(props);

        KogitoBuildContext context = JavaKogitoBuildContext.builder()
                .withApplicationProperties(props)
                .build();

        return DecisionCodegen.ofPath(context, Paths.get(path).toAbsolutePath());
    }

    @Test
    public void testDefault() throws Exception {
        DecisionCodegen codeGenerator = codeGenerator("src/test/resources/decision-validation-duplicateName",
                p -> {
                });
        assertThrows(RuntimeException.class,
                () -> {
                    codeGenerator.generate();
                },
                "Expected Validation would have failed for defaults.");
    }

    @Test
    public void testIgnore() throws Exception {
        DecisionCodegen codeGenerator = codeGenerator("src/test/resources/decision-validation-duplicateName",
                p -> p.setProperty(DecisionCodegen.VALIDATION_CONFIGURATION_KEY, "IGNORE"));
        Collection<GeneratedFile> files = codeGenerator.generate();
        Assertions.assertThat(files).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    public void testDisabled() throws Exception {
        DecisionCodegen codeGenerator = codeGenerator("src/test/resources/decision-validation-duplicateName",
                p -> p.setProperty(DecisionCodegen.VALIDATION_CONFIGURATION_KEY, "DISABLED"));
        Collection<GeneratedFile> files = codeGenerator.generate();
        Assertions.assertThat(files).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    public void testDTAnalysisDefault() throws Exception {
        DecisionCodegen codeGenerator = codeGenerator("src/test/resources/decision-validation-DTsemanticError",
                p -> {
                });
        assertThrows(RuntimeException.class,
                () -> {
                    codeGenerator.generate();
                },
                "Expected Validation would have failed for defaults.");
    }

    @Test
    public void testDTAnalysisIgnore() throws Exception {
        DecisionCodegen codeGenerator = codeGenerator("src/test/resources/decision-validation-DTsemanticError",
                p -> p.setProperty(DecisionCodegen.VALIDATION_CONFIGURATION_KEY, "IGNORE"));
        Collection<GeneratedFile> files = codeGenerator.generate();
        Assertions.assertThat(files).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    public void testDTAnalysisDisabled() throws Exception {
        DecisionCodegen codeGenerator = codeGenerator("src/test/resources/decision-validation-DTsemanticError",
                p -> p.setProperty(DecisionCodegen.VALIDATION_CONFIGURATION_KEY, "DISABLED"));
        Collection<GeneratedFile> files = codeGenerator.generate();
        Assertions.assertThat(files).hasSizeGreaterThanOrEqualTo(1);
    }
}
