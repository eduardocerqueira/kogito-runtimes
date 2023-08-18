package org.kie.kogito.quarkus.rules.deployment;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.kie.drl.engine.runtime.mapinput.service.KieRuntimeServiceDrlMapInput;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

/**
 * Main class of the Kogito rules extension
 */
public class RulesAssetsProcessor {

    private static final Logger LOGGER = Logger.getLogger(RulesAssetsProcessor.class);

    @BuildStep
    FeatureBuildItem featureBuildItem() {
        return new FeatureBuildItem("kogito-rules");
    }

    @BuildStep
    public List<ReflectiveClassBuildItem> reflectiveEfestoRules() {
        LOGGER.debug("reflectiveEfestoRules()");
        final List<ReflectiveClassBuildItem> toReturn = new ArrayList<>();
        toReturn.add(new ReflectiveClassBuildItem(true, true, KieRuntimeServiceDrlMapInput.class));
        LOGGER.debugf("toReturn {}", toReturn.size());
        return toReturn;
    }

}
