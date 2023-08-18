package org.kie.kogito.quarkus.config;

import java.util.Optional;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.config.StaticConfigBean;

@Singleton
public class ConfigBean extends StaticConfigBean {

    @Inject
    Instance<KogitoRuntimeConfig> runtimeConfig;

    @Inject
    Instance<KogitoBuildTimeConfig> buildTimeConfig;

    @Inject
    KogitoGAV gav;

    @Override
    public String getServiceUrl() {
        return runtimeConfig.get().serviceUrl.orElse("");
    }

    @Override
    public short processInstanceLimit() {
        return runtimeConfig.get().processInstanceLimit;
    }

    @Override
    public Optional<KogitoGAV> getGav() {
        return Optional.ofNullable(gav);
    }

    @Override
    public boolean failOnEmptyBean() {
        return buildTimeConfig.get().failOnEmptyBean;
    }

    @Override
    public boolean useCloudEvents() {
        return buildTimeConfig.get().useCloudEvents;
    }
}
