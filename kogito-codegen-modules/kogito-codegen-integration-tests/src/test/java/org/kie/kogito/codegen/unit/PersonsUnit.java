package org.kie.kogito.codegen.unit;

import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.conf.DefaultEntryPoint;
import org.kie.kogito.codegen.data.Person;

public class PersonsUnit implements RuleUnitData {

    @DefaultEntryPoint
    private DataStore<Person> persons;

    public PersonsUnit(DataStore<Person> persons) {
        this.persons = persons;
    }

    public DataStore<Person> getPersons() {
        return persons;
    }
}
