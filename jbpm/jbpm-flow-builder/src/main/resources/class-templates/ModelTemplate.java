package org.jbpm.process.codegen;

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;

import java.util.Map;
import java.util.HashMap;

import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;

public class XXXModel implements org.kie.kogito.Model, MapInput, MapInputId, MapOutput, MappableToModel<$modelClass$> {

    private String id;

    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }

}