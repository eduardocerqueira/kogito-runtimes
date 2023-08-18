package org.kie.efesto.quarkus.deployment;

import java.util.Collection;

import org.drools.codegen.common.GeneratedFile;

import io.quarkus.builder.item.MultiBuildItem;

public final class EfestoGeneratedClassBuildItem extends MultiBuildItem {

    private final Collection<GeneratedFile> generatedFiles;

    public EfestoGeneratedClassBuildItem(Collection<GeneratedFile> generatedFiles) {
        this.generatedFiles = generatedFiles;
    }

    public Collection<GeneratedFile> getGeneratedFiles() {
        return generatedFiles;
    }
}
