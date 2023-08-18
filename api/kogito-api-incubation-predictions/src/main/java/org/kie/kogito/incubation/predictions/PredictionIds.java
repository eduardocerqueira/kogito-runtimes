package org.kie.kogito.incubation.predictions;

import org.kie.kogito.incubation.common.ComponentRoot;

public class PredictionIds implements ComponentRoot {
    public LocalPredictionId get(String fileName, String name) {
        return new LocalPredictionId(fileName, name);
    }
}
