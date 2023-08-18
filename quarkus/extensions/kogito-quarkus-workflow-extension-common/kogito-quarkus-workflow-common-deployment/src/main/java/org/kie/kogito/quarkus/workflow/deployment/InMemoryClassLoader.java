package org.kie.kogito.quarkus.workflow.deployment;

import java.util.HashMap;
import java.util.Map;

public class InMemoryClassLoader extends ClassLoader {
    private Map<String, byte[]> classes = new HashMap<>();

    public InMemoryClassLoader(ClassLoader parent, Map<String, byte[]> classes) {
        super(parent);
        this.classes.putAll(classes);
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        byte[] byteClass = classes.remove(name);
        if (byteClass != null) {
            return defineClass(name, byteClass, 0, byteClass.length);
        }
        return super.findClass(name);
    }
}