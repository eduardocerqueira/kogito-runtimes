package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

import java.time.LocalTime;

import org.kie.kogito.KogitoGAV;

import io.micrometer.core.instrument.MeterRegistry;

public class LocalTimeHandler extends TypeHandlerWithSummary<LocalTime> {

    private final String dmnType;

    public LocalTimeHandler(String dmnType, KogitoGAV gav, MeterRegistry meterRegistry) {
        this.dmnType = dmnType;
        setRegistry(meterRegistry);
        setKogitoGAV(gav);
    }

    @Override
    public void record(String type, String endpointName, LocalTime sample) {
        getDefaultSummary(dmnType, type, endpointName).record(sample.toSecondOfDay());
    }

    @Override
    public String getDmnType() {
        return dmnType;
    }
}
