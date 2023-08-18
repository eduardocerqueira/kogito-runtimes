package org.kie.kogito.codegen.api;

public final class SourceFileCodegenBindEvent {

    private final String processId;

    private final String sourceFile;

    public SourceFileCodegenBindEvent(String processId, String sourceFile) {
        this.processId = processId;
        this.sourceFile = sourceFile;
    }

    public String getSourceFileId() {
        return processId;
    }

    public String getUri() {
        return sourceFile;
    }

    @Override
    public String toString() {
        return "SourceFileCodegenBindEvent{" +
                "processId='" + processId + '\'' +
                ", sourceFile='" + sourceFile + '\'' +
                '}';
    }
}
