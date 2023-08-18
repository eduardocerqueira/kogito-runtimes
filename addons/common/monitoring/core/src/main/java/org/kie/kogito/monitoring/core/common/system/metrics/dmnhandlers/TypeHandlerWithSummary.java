package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

import java.util.Arrays;

import org.kie.kogito.KogitoGAV;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

public abstract class TypeHandlerWithSummary<T> implements TypeHandler<T> {

    private MeterRegistry meterRegistry;
    private KogitoGAV gav;

    protected DistributionSummary getDefaultSummary(String dmnType, String decision, String endpoint) {
        return DistributionSummary
                .builder(dmnType.replace(" ", "_") + DecisionConstants.DECISIONS_NAME_SUFFIX)
                .description(DecisionConstants.DECISIONS_HELP)
                .publishPercentiles(DecisionConstants.SUMMARY_PERCENTILES)
                .distributionStatisticExpiry(DecisionConstants.SUMMARY_EXPIRATION)
                .tags(Arrays.asList(Tag.of("decision", decision), Tag.of("endpoint", endpoint),
                        Tag.of("artifactId", gav.getArtifactId()), Tag.of("version", gav.getVersion())))
                .register(meterRegistry);
    }

    protected void setRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    protected void setKogitoGAV(KogitoGAV gav) {
        this.gav = gav;
    }
}
