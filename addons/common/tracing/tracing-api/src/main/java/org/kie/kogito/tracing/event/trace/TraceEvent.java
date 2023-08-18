package org.kie.kogito.tracing.event.trace;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TraceEvent {

    @JsonProperty("header")
    private TraceHeader header;

    @JsonProperty("inputs")
    private List<TraceInputValue> inputs;

    @JsonProperty("outputs")
    private List<TraceOutputValue> outputs;

    @JsonProperty("executionSteps")
    private List<TraceExecutionStep> executionSteps;

    private TraceEvent() {
    }

    public TraceEvent(TraceHeader header, List<TraceInputValue> inputs, List<TraceOutputValue> outputs, List<TraceExecutionStep> executionSteps) {
        this.header = header;
        this.inputs = inputs;
        this.outputs = outputs;
        this.executionSteps = executionSteps;
    }

    public TraceHeader getHeader() {
        return header;
    }

    public List<TraceInputValue> getInputs() {
        return inputs;
    }

    public List<TraceOutputValue> getOutputs() {
        return outputs;
    }

    public List<TraceExecutionStep> getExecutionSteps() {
        return executionSteps;
    }
}
