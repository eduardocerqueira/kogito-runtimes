package org.kie.kogito.codegen.process;

import java.util.Optional;

import org.jbpm.compiler.canonical.ProcessMetaData;
import org.jbpm.compiler.canonical.ProcessToExecModelGenerator;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcess;

public class ProcessExecutableModelGenerator {

    private final KogitoWorkflowProcess workFlowProcess;
    private final ProcessToExecModelGenerator execModelGenerator;
    private String processFilePath;
    private ProcessMetaData processMetaData;

    public ProcessExecutableModelGenerator(KogitoWorkflowProcess workFlowProcess, ProcessToExecModelGenerator execModelGenerator) {
        this.workFlowProcess = workFlowProcess;
        this.execModelGenerator = execModelGenerator;
    }

    public boolean isPublic() {
        return KogitoWorkflowProcess.PUBLIC_VISIBILITY.equalsIgnoreCase(workFlowProcess.getVisibility());
    }

    public ProcessMetaData generate() {
        if (processMetaData != null) {
            return processMetaData;
        }
        processMetaData = execModelGenerator.generate(workFlowProcess);

        // this is ugly, but this class will be refactored
        String processClazzName = processMetaData.getProcessClassName();
        processFilePath = processClazzName.replace('.', '/') + ".java";
        return processMetaData;
    }

    public String description() {
        return Optional.ofNullable(workFlowProcess.getMetaData().get("Description"))
                .map(Object::toString).orElse("Executes " + workFlowProcess.getName());
    }

    public String className() {
        if (processMetaData == null) {
            generate();
        }
        return processMetaData.getProcessClassName();
    }

    public String generatedFilePath() {
        return processFilePath;
    }

    public String extractedProcessId() {
        return execModelGenerator.extractProcessId(workFlowProcess.getId());
    }

    public String getProcessId() {
        return workFlowProcess.getId();
    }

    public KogitoWorkflowProcess process() {
        return workFlowProcess;
    }
}
