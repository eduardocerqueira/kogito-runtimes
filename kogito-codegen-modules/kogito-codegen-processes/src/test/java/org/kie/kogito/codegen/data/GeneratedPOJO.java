package org.kie.kogito.codegen.data;

import org.kie.kogito.codegen.Generated;
import org.kie.kogito.codegen.VariableInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated(value = { "kogito-codegen" }, reference = "generatedPerson", name = "GeneratedPerson")
public class GeneratedPOJO {
    @VariableInfo(tags = "test")
    @JsonProperty("generatedProperty")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
