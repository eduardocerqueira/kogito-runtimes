package org.jbpm.workflow.core.impl;

import java.io.Serializable;

public class MultiInstanceSpecification implements Serializable {

    private static final long serialVersionUID = -608292180338609330L;

    private DataDefinition inputDataItem;
    private DataDefinition outputDataItem;
    private DataDefinition loopDataOutputRef;
    private DataDefinition loopDataInputRef;
    private String completionCondition;
    private boolean isSequential = false;

    public boolean hasMultiInstanceInput() {
        return loopDataInputRef != null;
    }

    public void setInputDataItem(DataDefinition inputDataItem) {
        this.inputDataItem = inputDataItem;
    }

    public DataDefinition getInputDataItem() {
        return inputDataItem;
    }

    public DataDefinition getOutputDataItem() {
        return outputDataItem;
    }

    public void setOutputDataItem(DataDefinition outputDataItem) {
        this.outputDataItem = outputDataItem;
    }

    public void setLoopDataOutputRef(DataDefinition loopDataOutputRef) {
        this.loopDataOutputRef = loopDataOutputRef;
    }

    public DataDefinition getLoopDataOutputRef() {
        return loopDataOutputRef;
    }

    public void setLoopDataInputRef(DataDefinition loopDataInputRef) {
        this.loopDataInputRef = loopDataInputRef;
    }

    public DataDefinition getLoopDataInputRef() {
        return loopDataInputRef;
    }

    public void setCompletionCondition(String completionCondition) {
        this.completionCondition = completionCondition;
    }

    public String getCompletionCondition() {
        return completionCondition;
    }

    public boolean hasLoopDataInputRef() {
        return loopDataInputRef != null;
    }

    public boolean hasLoopDataOutputRef() {
        return loopDataOutputRef != null;
    }

    public boolean isSequential() {
        return isSequential;
    }

    public void setSequential(boolean isSequential) {
        this.isSequential = isSequential;
    }
}
