package org.kie.kogito.codegen.data;

import org.kie.kogito.Model;

public class Travels implements Model {

    private String id;

    private Person person;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
