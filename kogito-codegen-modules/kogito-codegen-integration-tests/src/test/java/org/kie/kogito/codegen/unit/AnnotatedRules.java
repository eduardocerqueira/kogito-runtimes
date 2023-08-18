package org.kie.kogito.codegen.unit;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.annotation.When;
import org.kie.kogito.codegen.data.Person;

public class AnnotatedRules implements RuleUnitData {

    private final DataStore<Person> persons = DataSource.createStore();

    public void adult(@When("/persons[ age >= 18 ]") Person person) {
        System.out.println(person);
    }

    public DataStore<Person> getPersons() {
        return persons;
    }

    public AnnotatedRules getUnit() {
        return this;
    }
}
