package org.kie.kogito.serverless.workflow.workitemparams;

import java.util.function.UnaryOperator;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

public class ConfigSuppliedWorkItemResolver<T> extends ConfigWorkItemResolver<T> {

    private final UnaryOperator<T> transformer;

    public ConfigSuppliedWorkItemResolver(String key, Class<T> clazz, T defaultValue, UnaryOperator<T> transformer) {
        super(key, clazz, defaultValue);
        this.transformer = transformer;
    }

    @Override
    public T apply(KogitoWorkItem workitem) {
        return transformer.apply(super.apply(workitem));
    }

}
