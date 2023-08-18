package org.kie.kogito.serverless.workflow.models;

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;
import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;

public class JsonNodeModelOutput implements Model, MapInput, MapInputId, MapOutput, MappableToModel<JsonNodeModel> {

    private String id;
    private Object workflowdata;

    public JsonNodeModelOutput() {
    }

    public String getId() {
        return id;
    }

    public JsonNodeModelOutput(String id, Object workflowdata) {
        this.id = id;
        this.workflowdata = workflowdata;
    }

    public Object getWorkflowdata() {
        return workflowdata;
    }

    @Override
    public JsonNodeModel toModel() {
        return new JsonNodeModel(id, workflowdata);
    }
}
