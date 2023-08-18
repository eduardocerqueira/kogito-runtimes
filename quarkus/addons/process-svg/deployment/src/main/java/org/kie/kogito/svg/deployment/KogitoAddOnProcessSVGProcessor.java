package org.kie.kogito.svg.deployment;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourcePatternsBuildItem;
import io.quarkus.deployment.pkg.steps.NativeOrNativeSourcesBuild;

import static org.kie.kogito.svg.AbstractProcessSvgService.SVG_RELATIVE_PATH;

class KogitoAddOnProcessSVGProcessor {

    private static final String FEATURE = "kogito-addon-process-svg-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep(onlyIf = NativeOrNativeSourcesBuild.class)
    public void nativeResources(BuildProducer<NativeImageResourceBuildItem> resource, BuildProducer<NativeImageResourcePatternsBuildItem> resourcePatterns) {
        resourcePatterns.produce(NativeImageResourcePatternsBuildItem.builder().includeGlob(SVG_RELATIVE_PATH + "*.svg").build());
    }

}
