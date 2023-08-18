package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

import java.util.Arrays;

import org.kie.kogito.KogitoGAV;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

public class BooleanHandler implements TypeHandler<Boolean> {

    private final String dmnType;
    private final MeterRegistry meterRegistry;
    private final KogitoGAV gav;

    public BooleanHandler(String dmnType, KogitoGAV gav, MeterRegistry registry) {
        this.dmnType = dmnType;
        this.gav = gav;
        this.meterRegistry = registry;
    }

    @Override
    public void record(String decision, String endpointName, Boolean sample) {
        getCounter(decision, endpointName, gav, sample.toString()).increment();
    }

    @Override
    public String getDmnType() {
        return dmnType;
    }

    private Counter getCounter(String decision, String endpoint, KogitoGAV gav, String identifier) {
        return Counter
                .builder(dmnType + DecisionConstants.DECISIONS_NAME_SUFFIX)
                .description(DecisionConstants.DECISIONS_HELP)
                .tags(Arrays.asList(Tag.of("decision", decision), Tag.of("endpoint", endpoint),
                        Tag.of("identifier", identifier), Tag.of("artifactId", gav.getArtifactId()),
                        Tag.of("version", gav.getVersion())))
                .register(meterRegistry);
    }
}
