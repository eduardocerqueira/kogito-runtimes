package org.kie.kogito.codegen.rules.multiunit;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

public class MultiUnit implements RuleUnitData {
    DataStore<Object> values = DataSource.createStore();

    public DataStore<Object> getValues() {
        return values;
    }

    private DataStore<Object> thisMethodIsHidden() {
        return null;
    }
}
