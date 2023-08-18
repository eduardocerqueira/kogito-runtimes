package org.kie.kogito.serverless.workflow.parser;

public class VariableInfo {
    private final String inputVar;
    private final String outputVar;

    public VariableInfo(String inputVar, String outputVar) {
        this.inputVar = inputVar;
        this.outputVar = outputVar;
    }

    @Override
    public String toString() {
        return "VariableInfo [inputVar=" + inputVar + ", outputVar=" + outputVar + ']';
    }

    public String getInputVar() {
        return inputVar;
    }

    public String getOutputVar() {
        return outputVar;
    }
}
