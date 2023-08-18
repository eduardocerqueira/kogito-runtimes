package org.kie.kogito.codegen.rules.myunit;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.RuleUnitData;

public class MyUnit implements RuleUnitData {
    DataSource<Object> values;

    public DataSource<Object> getValues() {
        return values;
    }

    private DataSource<Object> thisMethodIsHidden() {
        return null;
    }
}
