package org.kie.kogito.monitoring.core.common.decision;

import java.util.function.BiFunction;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.decision.DecisionModel;

/**
 * This class must always have exact FQCN as <code>org.kie.kogito.monitoring.core.common.decision.MonitoredDecisionModelTransformer</code>
 * for code generation plugins to inject this class.
 */
public class MonitoredDecisionModelTransformer implements BiFunction<DecisionModel, KogitoGAV, DecisionModel> {
    @Override
    public DecisionModel apply(DecisionModel decisionModel, KogitoGAV gav) {
        return new MonitoredDecisionModel(decisionModel, gav);
    }
}
