package org.kie.kogito.core.decision.incubation.quarkus.support;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.kie.kogito.decision.DecisionModels;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.decisions.services.DecisionService;

@ApplicationScoped
public class QuarkusDecisionService implements DecisionService {
    @Inject
    Instance<DecisionModels> decisionModelsInstance;
    DecisionServiceImpl delegate;

    @PostConstruct
    void startup() {
        this.delegate = new DecisionServiceImpl(decisionModelsInstance.get());
    }

    @Override
    public ExtendedDataContext evaluate(LocalId decisionId, DataContext inputContext) {
        return delegate.evaluate(decisionId, inputContext);
    }
}
