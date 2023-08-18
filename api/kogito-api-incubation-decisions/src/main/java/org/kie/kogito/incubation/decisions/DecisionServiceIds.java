package org.kie.kogito.incubation.decisions;

public class DecisionServiceIds {
    private final LocalDecisionId parentId;

    public DecisionServiceIds(LocalDecisionId parentId) {
        this.parentId = parentId;
    }

    public LocalDecisionServiceId get(String serviceId) {
        return new LocalDecisionServiceId(parentId, serviceId);
    }
}
