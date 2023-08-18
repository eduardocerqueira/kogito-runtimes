package org.kie.kogito.codegen.process;

import java.util.List;

import org.jbpm.compiler.canonical.ProcessToExecModelGenerator;
import org.jbpm.compiler.canonical.UserTaskModelMetaData;
import org.kie.api.definition.process.WorkflowProcess;

public class UserTasksModelClassGenerator {

    private final WorkflowProcess workFlowProcess;
    private List<UserTaskModelMetaData> modelMetaData;

    public UserTasksModelClassGenerator(WorkflowProcess workFlowProcess) {
        this.workFlowProcess = workFlowProcess;
    }

    public List<UserTaskModelMetaData> generate() {
        // create model class for all variables
        modelMetaData = ProcessToExecModelGenerator.INSTANCE.generateUserTaskModel(workFlowProcess);
        return modelMetaData;
    }

    public static String generatedFilePath(String classname) {
        return classname.replace('.', '/') + ".java";
    }

}
