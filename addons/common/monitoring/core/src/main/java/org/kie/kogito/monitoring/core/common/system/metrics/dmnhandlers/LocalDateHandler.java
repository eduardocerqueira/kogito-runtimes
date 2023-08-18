package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

import java.time.LocalDate;
import java.time.ZoneOffset;

import org.kie.kogito.KogitoGAV;

import io.micrometer.core.instrument.MeterRegistry;

public class LocalDateHandler extends TypeHandlerWithSummary<LocalDate> {

    private final String dmnType;

    public LocalDateHandler(String dmnType, KogitoGAV gav, MeterRegistry registry) {
        this.dmnType = dmnType;
        setRegistry(registry);
        setKogitoGAV(gav);
    }

    @Override
    public void record(String type, String endpointName, LocalDate sample) {
        getDefaultSummary(dmnType, type, endpointName).record(sample.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
    }

    @Override
    public String getDmnType() {
        return dmnType;
    }
}
