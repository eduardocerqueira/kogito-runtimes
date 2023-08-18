package org.kie.kogito.quarkus.predictions.deployment;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.pmml.PMML4Result;
import org.kie.pmml.compiler.service.KieCompilerServicePMMLFile;
import org.kie.pmml.evaluator.core.executor.PMMLModelEvaluator;
import org.kie.pmml.evaluator.core.executor.PMMLModelEvaluatorFinder;
import org.kie.pmml.evaluator.core.executor.PMMLModelEvaluatorFinderImpl;
import org.kie.pmml.evaluator.core.service.KieRuntimeServicePMML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

/**
 * Main class of the Kogito predictions extension
 */
public class PredictionsAssetsProcessor {

    private static final Logger logger = LoggerFactory.getLogger(PredictionsAssetsProcessor.class);

    @BuildStep
    FeatureBuildItem featureBuildItem() {
        return new FeatureBuildItem("kogito-predictions");
    }

    @SuppressWarnings("rawtypes")
    @BuildStep
    public List<ReflectiveClassBuildItem> reflectivePredictions() {
        logger.debug("reflectivePredictions()");
        PMMLModelEvaluatorFinder pmmlModelEvaluatorFinder = new PMMLModelEvaluatorFinderImpl();
        final List<PMMLModelEvaluator> pmmlEvaluators = pmmlModelEvaluatorFinder.getImplementations(false);
        logger.debug("pmmlEvaluators {}", pmmlEvaluators.size());
        final List<ReflectiveClassBuildItem> toReturn = new ArrayList<>();
        toReturn.add(new ReflectiveClassBuildItem(true, true, PMML4Result.class));
        pmmlEvaluators.forEach(pmmlModelEvaluator -> toReturn.add(new ReflectiveClassBuildItem(true, true,
                pmmlModelEvaluator.getClass())));
        logger.debug("toReturn {}", toReturn.size());
        return toReturn;
    }

    @BuildStep
    public List<ReflectiveClassBuildItem> reflectiveEfestoPredictions() {
        logger.debug("reflectiveEfestoPredictions()");
        final List<ReflectiveClassBuildItem> toReturn = new ArrayList<>();
        toReturn.add(new ReflectiveClassBuildItem(true, true, KieRuntimeServicePMML.class));
        toReturn.add(new ReflectiveClassBuildItem(true, true, KieCompilerServicePMMLFile.class));
        toReturn.add(new ReflectiveClassBuildItem(true, true, KieCompilerServicePMMLFile.class));
        logger.debug("toReturn {}", toReturn.size());
        return toReturn;
    }

    @BuildStep
    public NativeImageResourceBuildItem predictionSPIEvaluator() {
        logger.debug("predictionSPIEvaluator()");
        return new NativeImageResourceBuildItem("META-INF/services/org.kie.pmml.evaluator.core.executor.PMMLModelEvaluator");
    }

    @BuildStep
    public NativeImageResourceBuildItem predictionSPIRuntime() {
        logger.debug("predictionSPIRuntime()");
        return new NativeImageResourceBuildItem("META-INF/services/org.kie.pmml.api.runtime.PMMLRuntime");
    }

}
