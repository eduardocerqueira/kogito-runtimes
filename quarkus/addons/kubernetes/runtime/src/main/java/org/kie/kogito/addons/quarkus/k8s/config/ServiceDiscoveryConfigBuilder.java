package org.kie.kogito.addons.quarkus.k8s.config;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.quarkus.runtime.configuration.ConfigBuilder;
import io.smallrye.config.SmallRyeConfigBuilder;

@RegisterForReflection
public class ServiceDiscoveryConfigBuilder implements ConfigBuilder {

    @Override
    public SmallRyeConfigBuilder configBuilder(SmallRyeConfigBuilder builder) {
        return builder.withInterceptors(new KubeDiscoveryConfigSourceInterceptor());
    }
}
