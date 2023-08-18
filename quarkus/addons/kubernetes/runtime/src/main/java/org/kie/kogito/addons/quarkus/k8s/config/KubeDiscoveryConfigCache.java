package org.kie.kogito.addons.quarkus.k8s.config;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class KubeDiscoveryConfigCache {

    private static final Logger logger = LoggerFactory.getLogger(KubeDiscoveryConfigCache.class);

    private final Map<String, String> cache = new ConcurrentHashMap<>();

    private final KubeDiscoveryConfigCacheUpdater updater;

    KubeDiscoveryConfigCache(KubeDiscoveryConfigCacheUpdater updater) {
        this.updater = updater;
    }

    Optional<String> get(String configName, String configValue) {
        try {
            String cachedValue = cache.computeIfAbsent(configName, k -> updater.update(configValue).map(URI::toString).orElse(null));
            return Optional.ofNullable(cachedValue);
        } catch (RuntimeException e) {
            logger.error("Service Discovery has failed on property [{}={}]", configName, configValue, e);
        }
        return Optional.ofNullable(configValue);
    }
}
