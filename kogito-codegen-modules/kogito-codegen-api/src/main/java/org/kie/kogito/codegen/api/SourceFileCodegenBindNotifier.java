package org.kie.kogito.codegen.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SourceFileCodegenBindNotifier {

    private final Collection<SourceFileCodegenBindListener> listeners = new ArrayList<>();

    public void addListeners(SourceFileCodegenBindListener... listeners) {
        Collections.addAll(this.listeners, listeners);
    }

    public void notify(SourceFileCodegenBindEvent event) {
        listeners.forEach(listener -> listener.onSourceFileCodegenBind(event));
    }
}
