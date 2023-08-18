package org.kie.kogito.quarkus.common.deployment;

import org.kie.internal.services.KieRuntimesImpl;
import org.kie.internal.services.KieWeaversImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

public class KogitoCommonProcessor {

    private static final Logger logger = LoggerFactory.getLogger(KogitoCommonProcessor.class);

    @BuildStep
    public NativeImageResourceBuildItem kieRuntimesSPIResource() {
        logger.debug("kieRuntimesSPIResource()");
        return new NativeImageResourceBuildItem("META-INF/services/org.kie.api.internal.runtime.KieRuntimes");
    }

    @BuildStep
    public NativeImageResourceBuildItem kieRuntimeServiceSPIResource() {
        logger.debug("kieRuntimeServiceSPIResource()");
        return new NativeImageResourceBuildItem("META-INF/services/org.kie.api.internal.runtime.KieRuntimeService");
    }

    @BuildStep
    public NativeImageResourceBuildItem kieWeaversSPIResource() {
        logger.debug("kieWeaversSPIResource()");
        return new NativeImageResourceBuildItem("META-INF/services/org.kie.api.internal.weaver.KieWeavers");
    }

    @BuildStep
    public NativeImageResourceBuildItem kieWeaverServiceSPIResource() {
        logger.debug("kieWeaverServiceSPIResource()");
        return new NativeImageResourceBuildItem("META-INF/services/org.kie.api.internal.weaver.KieWeaverService");
    }

    @BuildStep
    public ReflectiveClassBuildItem kieRuntimesImplReflectiveClass() {
        logger.debug("kieRuntimesImplReflectiveClass()");
        return new ReflectiveClassBuildItem(true, true, KieRuntimesImpl.class);
    }

    @BuildStep
    public ReflectiveClassBuildItem kieWeaversImplReflectiveClass() {
        logger.debug("kieWeaversImplReflectiveClass()");
        return new ReflectiveClassBuildItem(true, true, KieWeaversImpl.class);
    }
}
