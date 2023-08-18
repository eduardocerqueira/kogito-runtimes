package org.kie.kogito.config;

import java.util.Optional;

import org.kie.kogito.KogitoGAV;

public class StaticConfigBean implements ConfigBean {

    private String serviceUrl;
    private boolean useCloudEvents = true;
    private boolean failOnEmptyBean = false;
    private KogitoGAV gav;

    public StaticConfigBean() {
    }

    public StaticConfigBean(String serviceUrl, boolean useCloudEvents, KogitoGAV gav) {
        this.serviceUrl = serviceUrl;
        this.useCloudEvents = useCloudEvents;
        this.gav = gav;
    }

    protected void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    protected void setCloudEvents(boolean useCloudEvents) {
        this.useCloudEvents = useCloudEvents;
    }

    protected void setFailOnEmptyBean(boolean failOnEmptyBean) {
        this.failOnEmptyBean = failOnEmptyBean;
    }

    public void setGav(KogitoGAV gav) {
        this.gav = gav;
    }

    @Override
    public boolean useCloudEvents() {
        return useCloudEvents;
    }

    @Override
    public String getServiceUrl() {
        return serviceUrl;
    }

    @Override
    public Optional<KogitoGAV> getGav() {
        return Optional.ofNullable(gav);
    }

    @Override
    public boolean failOnEmptyBean() {
        return failOnEmptyBean;
    }
}
