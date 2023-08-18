package org.kie.kogito.explainability;

import java.util.Collection;
import java.util.List;

import org.kie.kogito.Application;
import org.kie.kogito.explainability.model.PredictInput;
import org.kie.kogito.explainability.model.PredictOutput;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public class ExplainabilityService {

    public static final ExplainabilityService INSTANCE = new ExplainabilityService(singletonList(new DecisionExplainabilityResourceExecutor()));

    private Collection<ExplainabilityResourceExecutor> executors;

    public ExplainabilityService(Collection<ExplainabilityResourceExecutor> executors) {
        this.executors = executors;
    }

    public List<PredictOutput> processRequest(Application application, List<PredictInput> predictInputs) {
        return predictInputs.stream().map(predictInput -> executors.stream()
                .filter(r -> r.acceptRequest(predictInput))
                .map(r -> r.processRequest(application, predictInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Malformed resourceType " + predictInput.getModelIdentifier().getResourceType())))
                .collect(toList());
    }
}
