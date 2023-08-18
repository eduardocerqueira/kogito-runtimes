package org.kie.kogito.integrationtests.springboot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Patient {

    @JsonProperty(value = "Is Covid Positive")
    private boolean covidPositive;

    @JsonProperty(value = "Diagnosis")
    private String diagnosis;

    public boolean isCovidPositive() {
        return covidPositive;
    }

    public void setCovidPositive(boolean covidPositive) {
        this.covidPositive = covidPositive;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
