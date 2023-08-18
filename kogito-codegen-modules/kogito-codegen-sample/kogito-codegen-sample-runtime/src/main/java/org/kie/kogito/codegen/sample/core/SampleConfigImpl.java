package org.kie.kogito.codegen.sample.core;

public class SampleConfigImpl implements SampleConfig {

    private int numberOfCopy = 1;

    public SampleConfigImpl() {

    }

    public SampleConfigImpl(int numberOfCopy) {
        this.numberOfCopy = numberOfCopy;
    }

    public void setNumberOfCopy(int numberOfCopy) {
        this.numberOfCopy = numberOfCopy;
    }

    @Override
    public int numberOfCopy() {
        return numberOfCopy;
    }
}