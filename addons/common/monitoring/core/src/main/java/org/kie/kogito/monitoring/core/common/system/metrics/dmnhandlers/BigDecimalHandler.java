package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

import java.math.BigDecimal;

import org.kie.kogito.KogitoGAV;

import io.micrometer.core.instrument.MeterRegistry;

public class BigDecimalHandler extends TypeHandlerWithSummary<BigDecimal> {

    private final String dmnType;

    public BigDecimalHandler(String dmnType, KogitoGAV gav, MeterRegistry registry) {
        this.dmnType = dmnType;
        setRegistry(registry);
        setKogitoGAV(gav);
    }

    @Override
    public void record(String decision, String endpointName, BigDecimal sample) {
        getDefaultSummary(dmnType, decision, endpointName).record(sample.doubleValue());
    }

    @Override
    public String getDmnType() {
        return dmnType;
    }
}
