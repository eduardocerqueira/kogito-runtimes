package org.kie.kogito.serverless.workflow.executor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jbpm.compiler.canonical.ReflectionUtils;
import org.kie.kogito.serverless.workflow.ServiceWorkItemHandler;

import static org.kie.kogito.serverless.workflow.SWFConstants.SERVICE_TASK_TYPE;

public class StaticServiceWorkItemHandler extends ServiceWorkItemHandler {

    @Override
    protected Object invoke(String className, String methodName, Object parameters) {
        try {
            ClassLoader cls = Thread.currentThread().getContextClassLoader();
            Class<?> clazz = cls.loadClass(className);
            Object[] args = parameters instanceof Map ? ((Map<String, Object>) parameters).values().toArray() : new Object[] { parameters };
            return ReflectionUtils.getMethod(cls, clazz, methodName, Stream.of(args).map(Object::getClass).map(Class::getName).collect(Collectors.toList())).invoke(getInstance(clazz),
                    args);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException(ex);
        }
    }

    protected Object getInstance(Class<?> clazz) throws ReflectiveOperationException {
        return clazz.getConstructor().newInstance();
    }

    @Override
    public String getName() {
        return SERVICE_TASK_TYPE;
    }
}
