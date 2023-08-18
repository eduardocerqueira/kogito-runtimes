package org.jbpm.process.instance.impl.humantask;

import java.util.Map;
import java.util.stream.Stream;

import org.jbpm.process.instance.impl.workitem.Abort;
import org.jbpm.process.instance.impl.workitem.Active;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;
import org.kie.kogito.process.workitem.LifeCycle;
import org.kie.kogito.process.workitem.LifeCyclePhase;
import org.kie.kogito.process.workitem.Transition;

/**
 * Work item handler to be used with human tasks (work items).
 * It uses <code>BaseHumanTaskLifeCycle</code> by default but allows to plug in
 * another life cycle implementation.
 *
 */
public class HumanTaskWorkItemHandler implements KogitoWorkItemHandler {

    private final LifeCycle<Map<String, Object>> lifeCycle;

    public HumanTaskWorkItemHandler() {
        this(new BaseHumanTaskLifeCycle());
    }

    public HumanTaskWorkItemHandler(LifeCycle<Map<String, Object>> lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        lifeCycle.transitionTo(workItem, manager, new HumanTaskTransition(Active.ID));
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        lifeCycle.transitionTo(workItem, manager, new HumanTaskTransition(Abort.ID));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void transitionToPhase(KogitoWorkItem workItem, KogitoWorkItemManager manager, Transition<?> transition) {
        lifeCycle.transitionTo(workItem, manager, (Transition<Map<String, Object>>) transition);
    }

    public static Stream<LifeCyclePhase> allowedPhases(KogitoWorkItemHandler handler, String phaseId) {
        if (handler instanceof HumanTaskWorkItemHandler) {
            return ((HumanTaskWorkItemHandler) handler).lifeCycle.allowedPhases(phaseId);
        }
        return null;
    }

}
