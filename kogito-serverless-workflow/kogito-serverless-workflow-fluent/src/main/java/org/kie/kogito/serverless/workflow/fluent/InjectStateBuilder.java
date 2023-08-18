package org.kie.kogito.serverless.workflow.fluent;

import com.fasterxml.jackson.databind.JsonNode;

import io.serverlessworkflow.api.states.DefaultState.Type;
import io.serverlessworkflow.api.states.InjectState;

public class InjectStateBuilder extends StateBuilder<InjectStateBuilder, InjectState> {

    protected InjectStateBuilder(JsonNode data) {
        super(new InjectState().withType(Type.INJECT).withData(data));
    }
}
