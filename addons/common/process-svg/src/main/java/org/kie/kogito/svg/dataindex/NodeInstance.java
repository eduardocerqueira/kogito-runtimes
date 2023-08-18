package org.kie.kogito.svg.dataindex;

import java.util.Objects;

public class NodeInstance {

    private Boolean completed;
    private String definitionId;

    public NodeInstance(Boolean completed, String definitionId) {
        this.completed = completed;
        this.definitionId = definitionId;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(String definitionId) {
        this.definitionId = definitionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NodeInstance)) {
            return false;
        }
        NodeInstance that = (NodeInstance) o;
        return completed.equals(that.completed) &&
                getDefinitionId().equals(that.getDefinitionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(completed, getDefinitionId());
    }

    @Override
    public String toString() {
        return "NodeInstance{" +
                "completed=" + completed +
                ", definitionId='" + definitionId + '\'' +
                '}';
    }
}
