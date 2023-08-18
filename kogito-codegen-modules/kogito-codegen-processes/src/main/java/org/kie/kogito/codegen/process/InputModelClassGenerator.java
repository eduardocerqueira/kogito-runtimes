package org.kie.kogito.codegen.process;

import org.jbpm.compiler.canonical.ModelMetaData;
import org.jbpm.compiler.canonical.ProcessToExecModelGenerator;
import org.kie.api.definition.process.WorkflowProcess;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

import static org.kie.kogito.internal.utils.ConversionUtils.sanitizeClassName;

public class InputModelClassGenerator {

    private final KogitoBuildContext context;
    private final WorkflowProcess workFlowProcess;
    private String className;
    private String modelFileName;
    private ModelMetaData modelMetaData;
    private String modelClassName;

    public InputModelClassGenerator(KogitoBuildContext context, WorkflowProcess workFlowProcess) {
        String pid = workFlowProcess.getId();
        className = sanitizeClassName(ProcessToExecModelGenerator.extractProcessId(pid) + "ModelInput");
        this.modelClassName = workFlowProcess.getPackageName() + "." + className;

        this.context = context;
        this.workFlowProcess = workFlowProcess;
    }

    public ModelMetaData generate() {
        // create model class for all variables
        modelMetaData = ProcessToExecModelGenerator.INSTANCE.generateInputModel(workFlowProcess);
        modelMetaData.setSupportsValidation(context.isValidationSupported());
        modelMetaData.setSupportsOpenApiGeneration(context.isOpenApiSpecSupported());

        modelFileName = modelMetaData.getModelClassName().replace('.', '/') + ".java";
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
