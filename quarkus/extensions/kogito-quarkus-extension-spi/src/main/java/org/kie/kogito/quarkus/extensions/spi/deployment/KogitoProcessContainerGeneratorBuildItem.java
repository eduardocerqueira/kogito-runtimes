package org.kie.kogito.quarkus.extensions.spi.deployment;

import java.util.Set;

import org.kie.kogito.codegen.process.ProcessContainerGenerator;

import io.quarkus.builder.item.MultiBuildItem;

public final class KogitoProcessContainerGeneratorBuildItem extends MultiBuildItem {

    private final Set<ProcessContainerGenerator> processContainerGenerators;

    public KogitoProcessContainerGeneratorBuildItem(Set<ProcessContainerGenerator> containers) {
        processContainerGenerators = containers;
    }

    public Set<ProcessContainerGenerator> getProcessContainerGenerators() {
        return processContainerGenerators;
    }
}
