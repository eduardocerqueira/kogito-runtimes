package org.kie.kogito.tracing.event.trace;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TraceResourceId {

    @JsonProperty("serviceUrl")
    private String serviceUrl;

    @JsonProperty("modelNamespace")
    private String modelNamespace;

    @JsonProperty("modelName")
    private String modelName;

    @JsonProperty("decisionServiceId")
    @JsonInclude(NON_NULL)
    private String decisionServiceId;

    @JsonProperty("decisionServiceName")
    @JsonInclude(NON_NULL)
    private String decisionServiceName;

    private TraceResourceId() {
    }

    public TraceResourceId(String serviceUrl, String modelNamespace, String modelName) {
        this(serviceUrl, modelNamespace, modelName, null, null);
    }

    public TraceResourceId(String serviceUrl, String modelNamespace, String modelName, String decisionServiceId, String decisionServiceName) {
        this.serviceUrl = serviceUrl;
        this.modelNamespace = modelNamespace;
        this.modelName = modelName;
        this.decisionServiceId = decisionServiceId;
        this.decisionServiceName = decisionServiceName;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public String getModelNamespace() {
        return modelNamespace;
    }

    public String getModelName() {
        return modelName;
    }

    public String getDecisionServiceId() {
        return decisionServiceId;
    }

    public String getDecisionServiceName() {
        return decisionServiceName;
    }
}
