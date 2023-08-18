package org.kie.kogito.incubation.rules;

import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUri;
import org.kie.kogito.incubation.common.LocalUriId;

public class RuleUnitId extends LocalUriId implements LocalId {
    public static final String PREFIX = "rule-units";

    private final String ruleUnitId;

    RuleUnitId(String ruleUnitId) {
        super(LocalUri.Root.append(PREFIX).append(ruleUnitId));
        this.ruleUnitId = ruleUnitId;
    }

    public String ruleUnitId() {
        return ruleUnitId;
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

    public RuleUnitInstanceIds instances() {
        return new RuleUnitInstanceIds(this);
    }

    public QueryIds queries() {
        return new QueryIds(this);
    }
}
