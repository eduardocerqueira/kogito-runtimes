package org.kie.kogito.quarkus.common.deployment;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;

import io.quarkus.builder.item.MultiBuildItem;

/**
 * {@link MultiBuildItem} for {@link KogitoBuildContext} attributes.
 */
public final class KogitoBuildContextAttributeBuildItem extends MultiBuildItem {

    private final String name;
    private final Object value;

    public KogitoBuildContextAttributeBuildItem(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
