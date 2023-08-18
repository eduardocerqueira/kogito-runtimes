package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.core.timer.Timer;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.TimerNode;

public class TimerNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends ExtendedNodeFactory<TimerNodeFactory<T>, T> {

    public static final String METHOD_TYPE = "type";
    public static final String METHOD_DELAY = "delay";
    public static final String METHOD_PERIOD = "period";
    public static final String METHOD_DATE = "date";

    public TimerNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new TimerNode(), id);
    }

    protected TimerNode getTimerNode() {
        return (TimerNode) getNode();
    }

    public TimerNodeFactory<T> type(int type) {
        Timer timer = getTimerNode().getTimer();
        if (timer == null) {
            timer = new Timer();
            getTimerNode().setTimer(timer);
        }
        timer.setTimeType(type);
        return this;
    }

    public TimerNodeFactory<T> delay(String delay) {
        Timer timer = getTimerNode().getTimer();
        if (timer == null) {
            timer = new Timer();
            getTimerNode().setTimer(timer);
        }
        timer.setDelay(delay);
        return this;
    }

    public TimerNodeFactory<T> period(String period) {
        Timer timer = getTimerNode().getTimer();
        if (timer == null) {
            timer = new Timer();
            getTimerNode().setTimer(timer);
        }
        timer.setPeriod(period);
        return this;
    }

    public TimerNodeFactory<T> date(String date) {
        Timer timer = getTimerNode().getTimer();
        if (timer == null) {
            timer = new Timer();
            getTimerNode().setTimer(timer);
        }
        timer.setDate(date);
        return this;
    }
}
