package org.kie.kogito.quarkus.common.deployment;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;

import io.quarkus.builder.item.SimpleBuildItem;

/**
 * {@link SimpleBuildItem} for {@link KogitoBuildContext} sharing among Quarkus extensions.
 */
public final class KogitoBuildContextBuildItem extends SimpleBuildItem {

    private final KogitoBuildContext kogitoBuildContext;

    public KogitoBuildContextBuildItem(KogitoBuildContext kogitoBuildContext) {
        this.kogitoBuildContext = kogitoBuildContext;
    }

    public KogitoBuildContext getKogitoBuildContext() {
        return kogitoBuildContext;
    }
}
