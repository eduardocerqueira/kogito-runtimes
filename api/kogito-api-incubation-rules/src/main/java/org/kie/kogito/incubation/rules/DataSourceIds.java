package org.kie.kogito.incubation.rules;

import org.kie.kogito.incubation.rules.data.DataSourceId;

public class DataSourceIds {
    private final RuleUnitInstanceId ruleUnitInstanceId;

    public DataSourceIds(RuleUnitInstanceId ruleUnitInstanceId) {
        this.ruleUnitInstanceId = ruleUnitInstanceId;
    }

    public DataSourceId get(String dataSourceId) {
        return new DataSourceId(ruleUnitInstanceId, dataSourceId);
    }
}
