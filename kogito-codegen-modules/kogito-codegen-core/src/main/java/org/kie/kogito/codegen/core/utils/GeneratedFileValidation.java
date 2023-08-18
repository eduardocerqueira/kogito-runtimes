package org.kie.kogito.codegen.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.drools.codegen.common.GeneratedFile;
import org.drools.codegen.common.GeneratedFileType;

public class GeneratedFileValidation {

    private GeneratedFileValidation() {
        // utility class
    }

    public static void validateGeneratedFileTypes(Collection<GeneratedFile> generatedFiles, Collection<GeneratedFileType.Category> expectedTypes) {
        Collection<GeneratedFile> unexpectedGeneratedFiles = generatedFiles.stream()
                .filter(generatedFile -> !expectedTypes.contains(generatedFile.category()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (!unexpectedGeneratedFiles.isEmpty()) {
            throw new IllegalStateException("Found unexpected files:\n" +
                    unexpectedGeneratedFiles.stream()
                            .map(x -> x.category().name() + " " +
                                    x.type().name() + ": "
                                    + x.relativePath())
                            .collect(Collectors.joining("\n")));
        }
    }
}
