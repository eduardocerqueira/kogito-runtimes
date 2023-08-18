package org.acme.travels;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.SingletonStore;

public class TravellerValidationService implements RuleUnitData {
    private final SingletonStore<Traveller> traveller = DataSource.createSingleton();

    public SingletonStore<Traveller> getTraveller() {
        return traveller;
    }
}
