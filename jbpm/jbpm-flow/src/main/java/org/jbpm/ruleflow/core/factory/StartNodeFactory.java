package org.jbpm.ruleflow.core.factory;

import java.util.List;

import org.jbpm.process.core.event.EventTypeFilter;
import org.jbpm.process.core.timer.Timer;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.impl.DataAssociation;
import org.jbpm.workflow.core.node.EventTrigger;
import org.jbpm.workflow.core.node.StartNode;

public class StartNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends NodeFactory<StartNodeFactory<T>, T> {

    public static final String METHOD_INTERRUPTING = "interrupting";
    public static final String METHOD_TRIGGER = "trigger";
    public static final String METHOD_TIMER = "timer";

    public StartNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new StartNode(), id);
    }

    protected StartNode getStartNode() {
        return (StartNode) getNode();
    }

    public StartNodeFactory<T> interrupting(boolean interrupting) {
        getStartNode().setInterrupting(interrupting);
        return this;
    }

    public StartNodeFactory<T> trigger(String triggerEventType, List<DataAssociation> dataAssociations) {
        EventTrigger trigger = new EventTrigger();
        EventTypeFilter eventFilter = new EventTypeFilter();
        eventFilter.setType(triggerEventType);
        trigger.addEventFilter(eventFilter);
        dataAssociations.forEach(trigger::addInAssociation);
        getStartNode().addTrigger(trigger);
        return this;
    }

    public StartNodeFactory<T> trigger(String triggerEventType, String target) {
        return trigger(triggerEventType, target, target);
    }

    public StartNodeFactory<T> trigger(String triggerEventType, String source, String target) {
        EventTrigger trigger = new EventTrigger();
        EventTypeFilter eventFilter = new EventTypeFilter();
        eventFilter.setType(triggerEventType);
        trigger.addEventFilter(eventFilter);
        if (source != null) {
            trigger.addInMapping(source, target);
        }
        getStartNode().addTrigger(trigger);
        return this;
    }

    public StartNodeFactory<T> timer(String delay, String period, String date, int timeType) {
        Timer timer = new Timer();
        timer.setDate(date);
        timer.setDelay(delay);
        timer.setPeriod(period);
        timer.setTimeType(timeType);

        getStartNode().setTimer(timer);
        return this;
    }
}
