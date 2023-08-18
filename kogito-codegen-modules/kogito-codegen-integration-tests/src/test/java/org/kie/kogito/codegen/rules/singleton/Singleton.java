package org.kie.kogito.codegen.rules.singleton;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;

public class Singleton implements RuleUnitData {
    SingletonStore<Datum> input = DataSource.createSingleton();
    SingletonStore<Datum> output = DataSource.createSingleton();

    public SingletonStore<Datum> getInput() {
        return input;
    }

    public SingletonStore<Datum> getOutput() {
        return output;
    }
}
