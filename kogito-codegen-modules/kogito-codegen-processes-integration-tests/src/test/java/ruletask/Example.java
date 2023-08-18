package ruletask;

import java.util.concurrent.atomic.AtomicInteger;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStream;
import org.drools.ruleunits.api.RuleUnitData;
import org.kie.kogito.codegen.data.Person;

public class Example implements RuleUnitData {

    String singleValue;
    DataStream<Person> persons = DataSource.createBufferedStream(16);
    private AtomicInteger counter = new AtomicInteger(0);

    public DataStream<Person> getPersons() {
        return persons;
    }

    public String getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(String singleValue) {
        this.singleValue = singleValue;
    }

    public AtomicInteger getCounter() {
        return counter;
    }

}
