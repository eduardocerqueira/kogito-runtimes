package org.kie.kogito.codegen.prediction;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.kie.efesto.common.api.model.GeneratedResources;
import org.kie.pmml.commons.model.KiePMMLModel;

public class PMMLResource {

    private final List<KiePMMLModel> kiePmmlModels;
    private final Path path;
    private final String modelPath;

    private final Map<String, byte[]> compiledClasses;

    private final Map<String, GeneratedResources> generatedResourcesMap;

    public PMMLResource(List<KiePMMLModel> kiePmmlModels,
            Path path,
            String modelPath,
            Map<String, byte[]> compiledClasses,
            Map<String, GeneratedResources> generatedResourcesMap) {
        this.kiePmmlModels = kiePmmlModels;
        this.path = path;
        this.modelPath = modelPath;
        this.compiledClasses = Collections.unmodifiableMap(compiledClasses);
        this.generatedResourcesMap = Collections.unmodifiableMap(generatedResourcesMap);
    }

    public List<KiePMMLModel> getKiePmmlModels() {
        return kiePmmlModels;
    }

    public Path getPath() {
        return path;
    }

    public String getModelPath() {
        return modelPath;
    }

    public Map<String, byte[]> getCompiledClasses() {
        return compiledClasses;
    }

    public Map<String, GeneratedResources> getGeneratedResourcesMap() {
        return generatedResourcesMap;
    }
}
