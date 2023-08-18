package org.jbpm.process.core.context.swimlane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jbpm.process.core.Context;
import org.jbpm.process.core.context.AbstractContext;

public class SwimlaneContext extends AbstractContext {

    private static final long serialVersionUID = 510l;

    public static final String SWIMLANE_SCOPE = "SwimlaneScope";

    private Map<String, Swimlane> swimlanes = new HashMap<>();

    public String getType() {
        return SWIMLANE_SCOPE;
    }

    public void addSwimlane(Swimlane swimlane) {
        this.swimlanes.put(swimlane.getName(), swimlane);
    }

    public Swimlane getSwimlane(String name) {
        return this.swimlanes.get(name);
    }

    public void removeSwimlane(String name) {
        this.swimlanes.remove(name);
    }

    public Collection<Swimlane> getSwimlanes() {
        return new ArrayList<>(swimlanes.values());
    }

    public void setSwimlanes(Collection<Swimlane> swimlanes) {
        this.swimlanes.clear();
        for (Swimlane swimlane : swimlanes) {
            addSwimlane(swimlane);
        }
    }

    public Context resolveContext(Object param) {
        if (param instanceof String) {
            return getSwimlane((String) param) == null ? null : this;
        }
        throw new IllegalArgumentException(
                "Swimlanes can only resolve swimlane names: " + param);
    }

}
