package org.kie.kogito.serverless.workflow.utils;

public class ConfigResolverHolder {

    private static ConfigResolver configResolver = new SystemPropertiesConfigResolver();

    public static void setConfigResolver(ConfigResolver secretResolver) {
        ConfigResolverHolder.configResolver = secretResolver;
    }

    public static ConfigResolver getConfigResolver() {
        return configResolver;
    }

    private ConfigResolverHolder() {
    }
}
