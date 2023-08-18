package acme;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

public class Hello implements RuleUnitData {
    DataStore<String> strings = DataSource.createStore();
    DataStore<Message> messages = DataSource.createStore();

    public DataStore<String> getStrings() {
        return strings;
    }
    
    public DataStore<Message> getMessages() {
        return messages;
    }
}