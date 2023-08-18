package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.kie.kogito.KogitoGAV;

import io.micrometer.core.instrument.MeterRegistry;

public class LocalDateTimeHandler extends TypeHandlerWithSummary<LocalDateTime> {

    private final String dmnType;

    public LocalDateTimeHandler(String dmnType, KogitoGAV gav, MeterRegistry meterRegistry) {
        this.dmnType = dmnType;
        setKogitoGAV(gav);
        setRegistry(meterRegistry);
    }

    @Override
    public void record(String type, String endpointName, LocalDateTime sample) {
        getDefaultSummary(dmnType, type, endpointName).record(sample.toInstant(ZoneOffset.UTC).toEpochMilli());
    }

    @Override
    public String getDmnType() {
        return dmnType;
    }
}
