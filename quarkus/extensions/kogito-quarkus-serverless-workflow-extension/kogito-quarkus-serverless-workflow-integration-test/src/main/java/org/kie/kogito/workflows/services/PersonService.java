package org.kie.kogito.workflows.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonService {

    public Person from(String name) {
        return new Person(name);
    }
}
