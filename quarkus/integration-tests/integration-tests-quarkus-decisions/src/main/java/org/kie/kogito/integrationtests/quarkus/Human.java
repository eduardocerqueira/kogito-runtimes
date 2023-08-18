package org.kie.kogito.integrationtests.quarkus;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Human {

    @JsonProperty(value = "Age")
    private BigDecimal age;

    @JsonProperty(value = "Name")
    private String name;

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
