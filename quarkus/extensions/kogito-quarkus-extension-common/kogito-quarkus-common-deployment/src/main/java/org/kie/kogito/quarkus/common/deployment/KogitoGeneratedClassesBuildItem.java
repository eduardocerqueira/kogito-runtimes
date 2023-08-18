package org.kie.kogito.quarkus.common.deployment;

import java.util.Collections;
import java.util.Map;

import org.jboss.jandex.IndexView;

import io.quarkus.builder.item.MultiBuildItem;

public final class KogitoGeneratedClassesBuildItem extends MultiBuildItem {

    private final IndexView indexedClasses;
    private final Map<String, byte[]> generatedClasses;

    public KogitoGeneratedClassesBuildItem(IndexView indexedClasses, Map<String, byte[]> generatedClasses) {
        this.indexedClasses = indexedClasses;
        this.generatedClasses = Collections.unmodifiableMap(generatedClasses);
    }

    public IndexView getIndexedClasses() {
        return indexedClasses;
    }

    public Map<String, byte[]> getGeneratedClasses() {
        return generatedClasses;
    }
}
