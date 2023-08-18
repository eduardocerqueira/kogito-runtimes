package org.kie.kogito.quarkus.common.deployment;

import java.util.Optional;

import org.jboss.jandex.IndexView;

import io.quarkus.builder.item.SimpleBuildItem;

public final class LiveReloadExecutionBuildItem extends SimpleBuildItem {

    private final IndexView indexView;
    private final ClassLoader classLoader;

    public LiveReloadExecutionBuildItem(IndexView indexView) {
        this(indexView, null);
    }

    public LiveReloadExecutionBuildItem(IndexView indexView, ClassLoader classLoader) {
        this.indexView = indexView;
        this.classLoader = classLoader;
    }

    public IndexView getIndexView() {
        return indexView;
    }

    public Optional<ClassLoader> getClassLoader() {
        return Optional.ofNullable(classLoader);
    }
}
