package org.kie.kogito.codegen.tests;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.DataStream;
import org.drools.ruleunits.api.RuleUnitData;
import org.kie.kogito.codegen.data.Person;

public class BusinessRuleUnit implements RuleUnitData {

    private DataStore<Person> persons = DataSource.createStore();
    private DataStream<String> strings = DataSource.createStream();
    private String myGlobal;

    public DataStore<Person> getPersons() {
        return persons;
    }

    public DataStream<String> getStrings() {
        return strings;
    }

    public void setMyGlobal(String myGlobal) {
        this.myGlobal = myGlobal;
    }

    public String getMyGlobal() {
        return myGlobal;
    }
}
