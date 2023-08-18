package org.jbpm.bpmn2.feel;

import java.util.ArrayList;
import java.util.List;

import org.kie.dmn.api.feel.runtime.events.FEELEvent;
import org.kie.dmn.api.feel.runtime.events.FEELEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeelErrorEvaluatorListener implements FEELEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(FeelErrorEvaluatorListener.class);

    private final List<FEELEvent> errorEvents = new ArrayList<>();

    @Override
    public void onEvent(FEELEvent event) {
        switch (event.getSeverity()) {
            case ERROR:
                errorEvents.add(event);
                LOG.error("{}", event);
                break;
            case TRACE:
                LOG.debug("{}", event);
                break;
            case WARN:
                LOG.warn("{}", event);
                break;
            case INFO:
            default:
                LOG.info("{}", event);
                break;
        }
    }

    public List<FEELEvent> getErrorEvents() {
        return errorEvents;
    }
}