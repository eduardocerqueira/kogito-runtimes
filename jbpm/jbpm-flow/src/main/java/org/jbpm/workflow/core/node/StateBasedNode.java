package org.jbpm.workflow.core.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.process.core.timer.Timer;
import org.jbpm.process.instance.impl.actions.CompleteStateBasedNodeInstanceAction;
import org.jbpm.workflow.core.DroolsAction;
import org.jbpm.workflow.core.impl.DroolsConsequenceAction;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;

import static org.jbpm.ruleflow.core.Metadata.ACTION;

public class StateBasedNode extends ExtendedNodeImpl {

    private static final long serialVersionUID = 510l;

    private Map<Timer, DroolsAction> timers;

    private List<String> boundaryEvents;

    private transient String duration;

    public Map<Timer, DroolsAction> getTimers() {
        return timers;
    }

    public void setTimeout(String duration) {
        this.duration = duration;
        Timer timer = new Timer();
        timer.setDelay(duration);
        DroolsConsequenceAction timeoutAction = new DroolsConsequenceAction("java", null);
        timeoutAction.setMetaData(ACTION, new CompleteStateBasedNodeInstanceAction(getNodeUniqueId()));
        addTimer(timer, timeoutAction);
    }

    public String getTimeout() {
        return duration;
    }

    public void addTimer(Timer timer, DroolsAction action) {
        if (timers == null) {
            timers = new HashMap<>();
        }
        if (timer.getId() == 0) {
            long id = 0;
            for (Timer t : timers.keySet()) {
                if (t.getId() > id) {
                    id = t.getId();
                }
            }
            timer.setId(++id);
        }
        timers.put(timer, action);
    }

    public void removeAllTimers() {
        if (timers != null) {
            timers.clear();
        }
    }

    public void addBoundaryEvents(String boundaryEvent) {
        if (this.boundaryEvents == null) {
            this.boundaryEvents = new ArrayList<>();
        }
        this.boundaryEvents.add(boundaryEvent);
    }

    public void setBoundaryEvents(List<String> boundaryEvents) {
        this.boundaryEvents = boundaryEvents;
    }

    public List<String> getBoundaryEvents() {
        return boundaryEvents;
    }

}
