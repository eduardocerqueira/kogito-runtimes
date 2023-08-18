package org.kie.kogito.workflows.services;

import java.util.Date;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AgePerson extends Person {

    private int age;
    private double income;
    private Date creationDate;

    private BasicDataPerson basicDataPerson;

    public AgePerson() {
    }

    public AgePerson(String name, int age, double income) {
        this(name, age, income, null);
    }

    public AgePerson(String name, int age, double income, Date creationDate) {
        super(name);
        this.age = age;
        this.income = income;
        this.creationDate = creationDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BasicDataPerson getBasicDataPerson() {
        return basicDataPerson;
    }

    public void setBasicDataPerson(BasicDataPerson basicDataPerson) {
        this.basicDataPerson = basicDataPerson;
    }
}
