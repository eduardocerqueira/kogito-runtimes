package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

import java.time.Duration;

import org.kie.kogito.KogitoGAV;

import io.micrometer.core.instrument.MeterRegistry;

public class DaysAndTimeDurationHandler extends TypeHandlerWithSummary<Duration> {

    private final String dmnType;

    public DaysAndTimeDurationHandler(String dmnType, KogitoGAV gav, MeterRegistry meterRegistry) {
        this.dmnType = dmnType;
        setRegistry(meterRegistry);
        setKogitoGAV(gav);
    }

    @Override
    public void record(String type, String endpointName, Duration sample) {
        getDefaultSummary(dmnType, type, endpointName).record(sample.toMillis());
    }

    @Override
    public String getDmnType() {
        return dmnType;
    }
}
