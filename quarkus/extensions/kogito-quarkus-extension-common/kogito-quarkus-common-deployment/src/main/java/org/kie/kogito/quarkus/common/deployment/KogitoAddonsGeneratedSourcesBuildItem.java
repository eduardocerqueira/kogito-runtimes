package org.kie.kogito.quarkus.common.deployment;

import java.util.Collection;

import org.drools.codegen.common.GeneratedFile;

import io.quarkus.builder.item.MultiBuildItem;

public abstract class KogitoAddonsGeneratedSourcesBuildItem extends MultiBuildItem implements Comparable<KogitoAddonsGeneratedSourcesBuildItem> {

    private static int counter;
    private final Collection<GeneratedFile> generatedFiles;
    private final int order;

    protected KogitoAddonsGeneratedSourcesBuildItem(Collection<GeneratedFile> generatedFiles) {
        this.generatedFiles = generatedFiles;
        this.order = counter++;
    }

    public Collection<GeneratedFile> getGeneratedFiles() {
        return generatedFiles;
    }

    @Override
    public int compareTo(KogitoAddonsGeneratedSourcesBuildItem o) {
        return order - o.order;
    }
}
