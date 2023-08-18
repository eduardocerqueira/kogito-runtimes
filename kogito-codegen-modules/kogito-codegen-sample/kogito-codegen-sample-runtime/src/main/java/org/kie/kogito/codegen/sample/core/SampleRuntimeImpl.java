package org.kie.kogito.codegen.sample.core;

import java.util.HashMap;
import java.util.Map;

import org.kie.kogito.Application;

public class SampleRuntimeImpl implements SampleRuntime {

    protected final Map<String, String> rawContent = new HashMap<>();
    protected SampleConfig config;

    public SampleRuntimeImpl() {

    }

    public SampleRuntimeImpl(Application application) {
        initApplication(application);
    }

    protected void initApplication(Application application) {
        this.config = application.config().get(SampleConfig.class);
    }

    protected void setConfig(SampleConfig config) {
        this.config = config;
    }

    public void addModels(Map<String, String> content) {
        this.rawContent.putAll(content);
    }

    @Override
    public SampleModel getModel(String name) {
        if (!rawContent.containsKey(name)) {
            throw new IllegalArgumentException("Impossible to find " + name);
        }
        if (config == null) {
            throw new IllegalStateException("No SampleConfig instance provided");
        }
        return new SampleModelImpl(name, rawContent.get(name), config.numberOfCopy());
    }
}
