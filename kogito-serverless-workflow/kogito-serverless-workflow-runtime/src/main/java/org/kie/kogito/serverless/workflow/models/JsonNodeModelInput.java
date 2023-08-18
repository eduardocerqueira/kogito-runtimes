package org.kie.kogito.serverless.workflow.models;

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;
import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;
import org.kie.kogito.jackson.utils.ObjectMapperFactory;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonNodeModelInput implements Model, MapInput, MapInputId, MapOutput, MappableToModel<JsonNodeModel> {

    private Object workflowdata;

    public Object getWorkflowdata() {
        return workflowdata;
    }

    @JsonAnySetter
    public void setData(String key, JsonNode value) {
        if (workflowdata == null) {
            workflowdata = ObjectMapperFactory.listenerAware().createObjectNode();
        }
        if (workflowdata instanceof ObjectNode) {
            ((ObjectNode) workflowdata).set(key, value);
        }
    }

    @Override
    public JsonNodeModel toModel() {
        return new JsonNodeModel(workflowdata);
    }
}
