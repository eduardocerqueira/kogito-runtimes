package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

import java.time.Period;

import org.kie.kogito.KogitoGAV;

import io.micrometer.core.instrument.MeterRegistry;

public class YearsAndMonthsDurationHandler extends TypeHandlerWithSummary<Period> {

    private final String dmnType;

    public YearsAndMonthsDurationHandler(String dmnType, KogitoGAV gav, MeterRegistry meterRegistry) {
        this.dmnType = dmnType;
        setRegistry(meterRegistry);
        setKogitoGAV(gav);
    }

    @Override
    public void record(String type, String endpointName, Period sample) {
        getDefaultSummary(dmnType, type, endpointName).record(sample.toTotalMonths());
    }

    @Override
    public String getDmnType() {
        return dmnType;
    }
}
