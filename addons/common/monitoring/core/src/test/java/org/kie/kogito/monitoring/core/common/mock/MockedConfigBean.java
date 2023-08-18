package org.kie.kogito.monitoring.core.common.mock;

import java.util.Optional;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.config.ConfigBean;

public class MockedConfigBean implements ConfigBean {

    @Override
    public boolean useCloudEvents() {
        return false;
    }

    @Override
    public String getServiceUrl() {
        return null;
    }

    @Override
    public Optional<KogitoGAV> getGav() {
        return Optional.of(KogitoGAV.EMPTY_GAV);
    }
}
