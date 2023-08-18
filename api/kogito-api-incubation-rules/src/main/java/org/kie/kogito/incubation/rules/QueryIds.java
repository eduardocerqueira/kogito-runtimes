package org.kie.kogito.incubation.rules;

public class QueryIds {
    private final RuleUnitId ruleUnitId;

    QueryIds(RuleUnitId ruleUnitId) {
        this.ruleUnitId = ruleUnitId;
    }

    public QueryId get(String queryId) {
        return new QueryId(ruleUnitId, queryId);
    }
}
