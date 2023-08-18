package org.jbpm.compiler.xml.core;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.compiler.xml.SemanticModule;

public class SemanticModules {
    public Map<String, SemanticModule> modules;

    public SemanticModules() {
        this.modules = new HashMap<>();
    }

    public void addSemanticModule(SemanticModule module) {
        this.modules.put(module.getUri(),
                module);
    }

    public SemanticModule getSemanticModule(String uri) {
        return this.modules.get(uri);
    }

    public String toString() {
        return this.modules.toString();
    }
}
