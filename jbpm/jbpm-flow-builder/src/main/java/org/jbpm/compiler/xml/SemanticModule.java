package org.jbpm.compiler.xml;

public interface SemanticModule {
    String getUri();

    void addHandler(String name, Handler handler);

    Handler getHandler(String name);

    Handler getHandlerByClass(Class<?> clazz);
}
