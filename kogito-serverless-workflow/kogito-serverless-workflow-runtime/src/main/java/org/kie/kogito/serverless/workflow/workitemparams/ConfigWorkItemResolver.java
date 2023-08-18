package org.kie.kogito.serverless.workflow.workitemparams;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.process.workitems.impl.WorkItemParamResolver;
import org.kie.kogito.serverless.workflow.utils.ConfigResolverHolder;

public class ConfigWorkItemResolver<T> implements WorkItemParamResolver<T> {

    private final String key;
    private final Class<T> clazz;
    private final T defaultValue;

    public ConfigWorkItemResolver(String key, Class<T> clazz, T defaultValue) {
        this.key = key;
        this.clazz = clazz;
        this.defaultValue = defaultValue;
    }

    @Override
    public T apply(KogitoWorkItem workitem) {
        return ConfigResolverHolder.getConfigResolver().getConfigProperty(key, clazz).orElse(defaultValue);
    }

}
