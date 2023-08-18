package org.kie.kogito.integrationtests.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FaceMaskTestData {
    @JsonProperty(value = "Human")
    private Human human;

    @JsonProperty(value = "Location")
    private String location;

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}