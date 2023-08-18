package org.kie.kogito.quarkus.rules.hotreload.newunit;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

/**
 * PersonUnit
 */
public class PersonUnit implements RuleUnitData {

    private DataStore<Person> persons = DataSource.createStore();

    public PersonUnit() {
    }

    public DataStore<Person> getPersons() {
        return persons;
    }

    public void setPersons(DataStore<Person> persons) {
        this.persons = persons;
    }

}
