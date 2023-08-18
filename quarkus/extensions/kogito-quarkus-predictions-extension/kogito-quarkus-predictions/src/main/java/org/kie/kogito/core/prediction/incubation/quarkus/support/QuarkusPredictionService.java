package org.kie.kogito.core.prediction.incubation.quarkus.support;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.predictions.services.PredictionService;
import org.kie.kogito.prediction.PredictionModels;

@ApplicationScoped
public class QuarkusPredictionService implements PredictionService {
    @Inject
    Instance<PredictionModels> predictionModels;
    PredictionServiceImpl delegate;

    @PostConstruct
    void startup() {
        this.delegate = new PredictionServiceImpl(predictionModels.get());
    }

    @Override
    public ExtendedDataContext evaluate(LocalId predictionId, DataContext inputContext) {
        return delegate.evaluate(predictionId, inputContext);
    }
}
