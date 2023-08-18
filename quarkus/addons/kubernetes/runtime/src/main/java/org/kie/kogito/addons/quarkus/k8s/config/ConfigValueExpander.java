package org.kie.kogito.addons.quarkus.k8s.config;

import org.kie.kogito.addons.k8s.resource.catalog.KubernetesProtocol;

import io.smallrye.config.ConfigValue;

class ConfigValueExpander {

    private final KubeDiscoveryConfigCache kubeDiscoveryConfigCache;

    ConfigValueExpander(KubeDiscoveryConfigCache kubeDiscoveryConfigCache) {
        this.kubeDiscoveryConfigCache = kubeDiscoveryConfigCache;
    }

    ConfigValue expand(ConfigValue configValue) {
        if (configValue == null || !valueContainsDiscovery(configValue)) {
            return configValue;
        }

        return kubeDiscoveryConfigCache.get(configValue.getName(), configValue.getValue())
                .map(configValue::withValue)
                .orElse(configValue);
    }

    private boolean valueContainsDiscovery(ConfigValue configValue) {
        for (KubernetesProtocol protocol : KubernetesProtocol.values()) {
            String value = configValue.getValue();
            if (value != null && value.startsWith(protocol.getValue() + ":")) {
                return true;
            }
        }
        return false;
    }
}
