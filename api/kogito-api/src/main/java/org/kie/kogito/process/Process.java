package org.kie.kogito.process;

import java.util.Collection;
import java.util.function.Predicate;

import org.kie.kogito.Model;
import org.kie.kogito.correlation.CompositeCorrelation;
import org.kie.kogito.correlation.CorrelationService;
import org.kie.kogito.internal.process.runtime.KogitoNode;

public interface Process<T> {

    ProcessInstance<T> createInstance(T workingMemory);

    ProcessInstance<T> createInstance(String businessKey, T workingMemory);

    ProcessInstance<T> createInstance(String businessKey, CompositeCorrelation correlation, T workingMemory);

    ProcessInstances<T> instances();

    Collection<KogitoNode> findNodes(Predicate<KogitoNode> filter);

    CorrelationService correlations();

    <S> void send(Signal<S> sig);

    T createModel();

    ProcessInstance<? extends Model> createInstance(Model m);

    ProcessInstance<? extends Model> createInstance(String businessKey, Model m);

    String id();

    String name();

    String version();

    String type();

    void activate();

    void deactivate();
}
