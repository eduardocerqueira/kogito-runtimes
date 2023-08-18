package org.jbpm.process.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Age")
    private int age;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
