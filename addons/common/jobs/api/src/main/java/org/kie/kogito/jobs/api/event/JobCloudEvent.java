package org.kie.kogito.jobs.api.event;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Deprecated
public abstract class JobCloudEvent<T> {

    static final String SPEC_VERSION = "1.0";
    @JsonProperty("specversion")
    private String specVersion;
    private String id;
    private URI source;
    private String type;
    private ZonedDateTime time;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String subject;
    @JsonProperty("datacontenttype")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String dataContentType;
    @JsonProperty("dataschema")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String dataSchema;

    private T data;

    protected JobCloudEvent() {
        // marshalling constructor.
    }

    public URI getSource() {
        return source;
    }

    public String getSpecVersion() {
        return specVersion;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public T getData() {
        return data;
    }

    public String getDataContentType() {
        return dataContentType;
    }

    public String getDataSchema() {
        return dataSchema;
    }

    public String getSubject() {
        return subject;
    }

    public void setSpecVersion(String specVersion) {
        this.specVersion = specVersion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSource(URI source) {
        this.source = source;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
    }

    public void setDataSchema(String dataSchema) {
        this.dataSchema = dataSchema;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected void assertExpectedType(String currentType, String expectedType) {
        if (!Objects.equals(currentType, expectedType)) {
            throw new IllegalArgumentException(getClass().getName() + " don't support other event type than: "
                    + expectedType + ", please don't use this value: " + currentType);
        }
    }

    @Override
    public String toString() {
        return "JobCloudEvent{" +
                "specVersion='" + specVersion + '\'' +
                ", id='" + id + '\'' +
                ", source=" + source +
                ", type='" + type + '\'' +
                ", time=" + time +
                ", subject='" + subject + '\'' +
                ", dataContentType='" + dataContentType + '\'' +
                ", dataSchema='" + dataSchema + '\'' +
                ", data=" + data +
                '}';
    }
}
