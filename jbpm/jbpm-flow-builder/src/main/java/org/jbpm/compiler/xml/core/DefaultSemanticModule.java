package org.jbpm.compiler.xml.core;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.SemanticModule;

public class DefaultSemanticModule implements SemanticModule {
    public String uri;
    public Map<String, Handler> handlers;
    public Map<Class<?>, Handler> handlersByClass;

    public DefaultSemanticModule(String uri) {
        this.uri = uri;
        this.handlers = new HashMap<>();
        this.handlersByClass = new HashMap<>();
    }

    public String getUri() {
        return this.uri;
    }

    public void addHandler(String name, Handler handler) {
        this.handlers.put(name, handler);
        if (handler != null && handler.generateNodeFor() != null) {
            this.handlersByClass.put(handler.generateNodeFor(), handler);
        }
    }

    public Handler getHandler(String name) {
        return this.handlers.get(name);
    }

    public Handler getHandlerByClass(Class<?> clazz) {
        while (clazz != null) {
            Handler handler = this.handlersByClass.get(clazz);
            if (handler != null) {
                return handler;
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

}
