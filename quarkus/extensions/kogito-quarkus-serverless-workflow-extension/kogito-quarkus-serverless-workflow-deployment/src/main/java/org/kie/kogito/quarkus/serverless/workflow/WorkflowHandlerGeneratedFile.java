package org.kie.kogito.quarkus.serverless.workflow;

import java.nio.file.Path;
import java.util.Objects;

import org.drools.codegen.common.GeneratedFile;
import org.drools.codegen.common.GeneratedFileType;

public class WorkflowHandlerGeneratedFile extends GeneratedFile {

    private String workItemHandlerName;

    public WorkflowHandlerGeneratedFile(String workItemHandlerName, GeneratedFileType type, Path path, String contents) {
        super(type, path, contents);
        this.workItemHandlerName = workItemHandlerName;
    }

    public String getWorkItemHandlerName() {
        return workItemHandlerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        WorkflowHandlerGeneratedFile that = (WorkflowHandlerGeneratedFile) o;
        return workItemHandlerName.equals(that.workItemHandlerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), workItemHandlerName);
    }
}
