package org.kie.kogito.quarkus.common.deployment;

import java.util.Collection;

import org.drools.codegen.common.GeneratedFile;

import io.quarkus.builder.item.SimpleBuildItem;

public final class KogitoGeneratedSourcesBuildItem extends SimpleBuildItem {

    private final Collection<GeneratedFile> generatedFiles;

    public KogitoGeneratedSourcesBuildItem(Collection<GeneratedFile> generatedFiles) {
        this.generatedFiles = generatedFiles;
    }

    public Collection<GeneratedFile> getGeneratedFiles() {
        return generatedFiles;
    }

}
