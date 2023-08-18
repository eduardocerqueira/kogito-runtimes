package org.kie.kogito.workflows.services;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgePersonService {

    public AgePerson from(String name, int age, double income) {
        return new AgePerson(name, age, income);
    }

    public AgePerson createFrom(String name, int intValue, double income, long dateValue,
            String cardId, double discount, Boolean enabled) {
        Date receivedCreation = new Date(dateValue);
        Date receivedBirth = new Date(dateValue);
        AgePerson agePerson = new AgePerson(name, intValue, income, receivedCreation);
        agePerson.setBasicDataPerson(new BasicDataPerson(cardId, discount, intValue, enabled, receivedBirth));
        return agePerson;
    }
}
