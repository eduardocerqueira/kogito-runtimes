package org.kie.kogito.integrationtests.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HospitalsTestData {
    @JsonProperty(value = "Patient")
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}