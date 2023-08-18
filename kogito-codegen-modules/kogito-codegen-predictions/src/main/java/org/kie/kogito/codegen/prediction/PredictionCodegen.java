package org.kie.kogito.codegen.prediction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.drools.codegen.common.GeneratedFile;
import org.kie.efesto.common.api.model.GeneratedResources;
import org.kie.kogito.codegen.api.ApplicationSection;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.core.AbstractGenerator;
import org.kie.kogito.codegen.prediction.config.PredictionConfigGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.kie.kogito.codegen.prediction.PredictionCodegenUtils.generateModelFromGeneratedResources;
import static org.kie.kogito.codegen.prediction.PredictionCodegenUtils.generateModelsFromResource;

public class PredictionCodegen extends AbstractGenerator {

    public static final String GENERATOR_NAME = "predictions";
    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionCodegen.class);

    private final Collection<PMMLResource> resources;

    public PredictionCodegen(KogitoBuildContext context,
            Collection<PMMLResource> resources) {
        super(context, GENERATOR_NAME, new PredictionConfigGenerator(context));
        this.resources = resources;
    }

    @Override
    public Optional<ApplicationSection> section() {
        LOGGER.debug("section");
        return Optional.of(new PredictionModelsGenerator(context(), applicationCanonicalName(), resources));
    }

    @Override
    public boolean isEmpty() {
        return resources.isEmpty();
    }

    @Override
    public int priority() {
        return 40;
    }

    @Override
    public String applicationCanonicalName() {
        return super.applicationCanonicalName();
    }

    @Override
    protected Collection<GeneratedFile> internalGenerate() {
        LOGGER.debug("internalGenerate");
        Collection<GeneratedFile> files = new ArrayList<>();
        for (PMMLResource resource : resources) {
            generateModelsFromResource(files, resource, this);
            for (Map.Entry<String, GeneratedResources> generatedResourcesEntry : resource.getGeneratedResourcesMap().entrySet()) {
                generateModelFromGeneratedResources(files, generatedResourcesEntry);
            }
        }
        return files;
    }
}
