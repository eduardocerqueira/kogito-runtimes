package org.kie.kogito.serverless.workflow.utils;

import java.nio.file.Path;

public enum WorkflowFormat {
    JSON,
    YAML;

    private static final String JSON_EXTENSION = ".json";

    public static WorkflowFormat fromFileName(String fileName) {
        return fileName.endsWith(JSON_EXTENSION) ? JSON : YAML;
    }

    public static WorkflowFormat fromFileName(Path fileName) {
        return fromFileName(fileName.toString());
    }
}
