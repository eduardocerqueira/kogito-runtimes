package org.kie.kogito.integrationtests.quarkus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HospitalStatusTestData {

    @JsonProperty(value = "Hospital")
    private String hospital;

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
