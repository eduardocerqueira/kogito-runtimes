package org.kie.kogito.codegen.core.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.drools.codegen.common.GeneratedFile;
import org.drools.codegen.common.GeneratedFileType;
import org.junit.jupiter.api.Test;

class GeneratedFileValidationTest {

    @Test
    public void validateGeneratedFileTypes() {
        List<GeneratedFile> generatedFiles = Arrays.asList(
                new GeneratedFile(GeneratedFileType.SOURCE, "myPath1", ""),
                new GeneratedFile(GeneratedFileType.INTERNAL_RESOURCE, "myPath2", ""),
                new GeneratedFile(GeneratedFileType.COMPILED_CLASS, "myPath3", ""),
                new GeneratedFile(GeneratedFileType.STATIC_HTTP_RESOURCE, "myPath4", ""));

        GeneratedFileValidation.validateGeneratedFileTypes(generatedFiles, Arrays.asList(
                GeneratedFileType.Category.SOURCE,
                GeneratedFileType.Category.INTERNAL_RESOURCE,
                GeneratedFileType.Category.STATIC_HTTP_RESOURCE,
                GeneratedFileType.Category.COMPILED_CLASS));
        Set<GeneratedFileType.Category> categories = Collections.singleton(GeneratedFileType.Category.SOURCE);
        Assertions.assertThatThrownBy(() -> GeneratedFileValidation.validateGeneratedFileTypes(generatedFiles, categories))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("myPath2")
                .hasMessageContaining("myPath3")
                .hasMessageContaining("myPath4");
    }

    @Test
    public void validateGeneratedFileWithMetaInfResource() {
        List<GeneratedFile> generatedFiles = Arrays.asList(
                new GeneratedFile(GeneratedFileType.STATIC_HTTP_RESOURCE, "META-INF/resources/myPath1", ""));

        // validation is okay even if WARN is logged
        GeneratedFileValidation.validateGeneratedFileTypes(generatedFiles, Arrays.asList(
                GeneratedFileType.Category.STATIC_HTTP_RESOURCE));
    }
}
