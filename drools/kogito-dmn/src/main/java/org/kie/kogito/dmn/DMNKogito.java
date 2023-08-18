package org.kie.kogito.dmn;

import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.drools.io.ReaderResource;
import org.kie.api.io.Resource;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.core.internal.utils.DMNEvaluationUtils;
import org.kie.dmn.core.internal.utils.DMNEvaluationUtils.DMNEvaluationResult;
import org.kie.dmn.core.internal.utils.DMNRuntimeBuilder;
import org.kie.dmn.feel.util.EvalHelper;
import org.kie.kogito.Application;
import org.kie.kogito.dmn.rest.KogitoDMNResult;

/**
 * Internal Utility class.<br/>
 * Use {@link Application#decisionModels()} of Kogito API to programmatically access DMN assets and evaluate DMN
 * decisions.
 */
public class DMNKogito {

    private DMNKogito() {
        // intentionally private.
    }

    /**
     * Internal Utility class.<br/>
     * Use {@link Application#decisionModels()} of Kogito API to programmatically access DMN assets and evaluate DMN
     * decisions.
     */
    public static DMNRuntime createGenericDMNRuntime(Reader... readers) {
        DMNKogitoCallbacks.beforeCreateGenericDMNRuntime(readers);
        List<Resource> resources = Stream.of(readers).map(ReaderResource::new).collect(Collectors.toList());
        EvalHelper.clearGenericAccessorCache(); // KOGITO-3325 DMN hot reload manage accessor cache when stronglytyped
        DMNRuntime dmnRuntime = DMNRuntimeBuilder.fromDefaults()
                .buildConfiguration()
                .fromResources(resources)
                .getOrElseThrow(e -> new RuntimeException("Error initializing DMNRuntime", e));
        DMNKogitoCallbacks.afterCreateGenericDMNRuntime(dmnRuntime);
        return dmnRuntime;
    }

    public static DMNModel modelByName(DMNRuntime dmnRuntime, String modelName) {
        List<DMNModel> modelsWithName =
                dmnRuntime.getModels().stream().filter(m -> modelName.equals(m.getName())).collect(Collectors.toList());
        if (modelsWithName.size() == 1) {
            return modelsWithName.get(0);
        } else {
            throw new RuntimeException("Multiple model with the same name: " + modelName);
        }
    }

    public static KogitoDMNResult evaluate(DMNRuntime dmnRuntime, String modelName, Map<String, Object> dmnContext) {
        return evaluate(dmnRuntime, modelByName(dmnRuntime, modelName).getNamespace(), modelName, dmnContext);
    }

    public static KogitoDMNResult evaluate(DMNRuntime dmnRuntime, String modelNamespace, String modelName, Map<String, Object> dmnContext) {
        DMNEvaluationResult evaluationResult = DMNEvaluationUtils.evaluate(dmnRuntime,
                modelNamespace,
                modelName,
                dmnContext,
                null,
                null,
                null);
        return new KogitoDMNResult(modelNamespace, modelName, evaluationResult.result);
    }

}
