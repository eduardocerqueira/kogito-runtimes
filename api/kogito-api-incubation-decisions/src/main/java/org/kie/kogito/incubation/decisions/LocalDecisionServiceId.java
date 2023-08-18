package org.kie.kogito.incubation.decisions;

import org.kie.kogito.incubation.common.Id;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUriId;

public class LocalDecisionServiceId extends LocalUriId implements LocalId {
    public static final String PREFIX = "services";

    private final Id decisionId;
    private final String serviceId;

    public LocalDecisionServiceId(Id decisionId, String serviceId) {
        super(decisionId.toLocalId().asLocalUri().append(PREFIX).append(serviceId));
        LocalId localDecisionId = decisionId.toLocalId();
        if (!localDecisionId.toLocalId().asLocalUri().startsWith(LocalDecisionId.PREFIX)) {
            throw new IllegalArgumentException("Not a valid decision path"); // fixme use typed exception
        }
        this.decisionId = decisionId;
        this.serviceId = serviceId;
    }

    public Id decisionId() {
        return decisionId;
    }

    public String serviceId() {
        return serviceId;
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

}
