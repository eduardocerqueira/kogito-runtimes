package org.kie.kogito.workflows.services;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name + "Person";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
