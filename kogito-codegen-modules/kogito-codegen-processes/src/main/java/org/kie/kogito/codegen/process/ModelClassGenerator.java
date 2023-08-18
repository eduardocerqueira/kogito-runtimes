package org.kie.kogito.codegen.process;

import org.jbpm.compiler.canonical.ModelMetaData;
import org.jbpm.compiler.canonical.ProcessToExecModelGenerator;
import org.kie.api.definition.process.WorkflowProcess;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcess;
import org.kie.kogito.serverless.workflow.utils.ServerlessWorkflowUtils;

public class ModelClassGenerator {

    private final String modelFileName;
    private final ModelMetaData modelMetaData;
    private final String modelClassName;

    public ModelClassGenerator(KogitoBuildContext context, WorkflowProcess workFlowProcess) {
        modelMetaData = workFlowProcess.getType().equals(KogitoWorkflowProcess.SW_TYPE) ? ServerlessWorkflowUtils.getModelMetadata(workFlowProcess)
                : ProcessToExecModelGenerator.INSTANCE.generateModel(workFlowProcess);
        modelClassName = modelMetaData.getModelClassName();
        modelFileName = modelMetaData.getModelClassName().replace('.', '/') + ".java";
        modelMetaData.setSupportsValidation(context.isValidationSupported());
        modelMetaData.setSupportsOpenApiGeneration(context.isOpenApiSpecSupported());
    }

    public ModelMetaData generate() {
        return modelMetaData;
    }

    public String generatedFilePath() {
        return modelFileName;
    }

    public String simpleName() {
        return modelMetaData.getModelClassSimpleName();
    }

    public String className() {
        return modelClassName;
    }
}
