package org.jbpm.process.instance.context.swimlane;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.process.core.context.swimlane.SwimlaneContext;
import org.jbpm.process.instance.context.AbstractContextInstance;

public class SwimlaneContextInstance extends AbstractContextInstance {

    private static final long serialVersionUID = 510l;

    private Map<String, String> swimlaneActors = new HashMap<>();

    public String getContextType() {
        return SwimlaneContext.SWIMLANE_SCOPE;
    }

    public SwimlaneContext getSwimlaneContext() {
        return (SwimlaneContext) getContext();
    }

    public String getActorId(String swimlane) {
        return swimlaneActors.get(swimlane);
    }

    public void setActorId(String swimlane, String actorId) {
        swimlaneActors.put(swimlane, actorId);
    }

    public Map<String, String> getSwimlaneActors() {
        return swimlaneActors;
    }

}
