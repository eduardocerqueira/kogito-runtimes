package org.kie.kogito.pmml;

import java.util.Map;

import org.kie.api.pmml.PMML4Result;
import org.kie.api.pmml.PMMLRequestData;
import org.kie.kogito.prediction.PredictionModel;
import org.kie.memorycompiler.KieMemoryCompiler;
import org.kie.pmml.api.models.PMMLModel;
import org.kie.pmml.api.runtime.PMMLRuntime;
import org.kie.pmml.api.runtime.PMMLRuntimeContext;
import org.kie.pmml.evaluator.core.PMMLRuntimeContextImpl;

import static org.kie.kogito.pmml.PMMLKogito.modelByName;
import static org.kie.kogito.pmml.utils.PMMLUtils.getPMMLRequestData;

public class PmmlPredictionModel implements PredictionModel {

    private final PMMLRuntime pmmlRuntime;
    private final PMMLModel pmmlModel;

    public PmmlPredictionModel(PMMLRuntime pmmlRuntime, String fileName, String modelName) {
        this.pmmlRuntime = pmmlRuntime;
        this.pmmlModel = modelByName(pmmlRuntime, fileName, modelName);
        if (this.pmmlModel == null) {
            String exceptionString = String.format("PMML model %s@%s not found in the inherent " +
                    "PMMLRuntime.", modelName, fileName);
            throw new IllegalStateException(exceptionString);
        }
    }

    @Override
    public PMMLRuntimeContext newContext(Map<String, Object> variables) {
        final PMMLRequestData pmmlRequestData = getPMMLRequestData(pmmlModel.getName(), variables);
        KieMemoryCompiler.MemoryCompilerClassLoader memoryCompilerClassLoader =
                new KieMemoryCompiler.MemoryCompilerClassLoader(Thread.currentThread().getContextClassLoader());
        return new PMMLRuntimeContextImpl(pmmlRequestData, pmmlModel.getFileName(), memoryCompilerClassLoader);
    }

    @Override
    public PMML4Result evaluateAll(PMMLRuntimeContext context) {
        return pmmlRuntime.evaluate(pmmlModel.getName(), context);
    }

    @Override
    public PMMLModel getPMMLModel() {
        return pmmlModel;
    }

}
