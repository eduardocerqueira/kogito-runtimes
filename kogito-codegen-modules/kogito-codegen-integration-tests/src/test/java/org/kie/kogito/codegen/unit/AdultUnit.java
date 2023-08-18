package org.kie.kogito.codegen.unit;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.conf.Clock;
import org.drools.ruleunits.api.conf.ClockType;
import org.drools.ruleunits.api.conf.SessionsPool;
import org.kie.kogito.codegen.data.Person;
import org.kie.kogito.codegen.data.Results;

@SessionsPool(1)
@Clock(ClockType.PSEUDO)
public class AdultUnit implements RuleUnitData {
    private int adultAge = 18;
    private DataStore<Person> persons = DataSource.createStore();
    private Results results = new Results();

    public AdultUnit() {
        this(DataSource.createStore());
    }

    public AdultUnit(DataStore<Person> persons) {
        this.persons = persons;
    }

    public AdultUnit(DataStore<Person> persons, int adultAge) {
        this.persons = persons;
        this.adultAge = adultAge;
    }

    public DataStore<Person> getPersons() {
        return persons;
    }

    public int getAdultAge() {
        return adultAge;
    }

    public void setAdultAge(int adultAge) {
        this.adultAge = adultAge;
    }

    public Results getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "AdultUnit(" + adultAge + ")";
    }
}
