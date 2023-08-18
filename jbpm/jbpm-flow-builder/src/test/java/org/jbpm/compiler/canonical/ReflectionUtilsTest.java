package org.jbpm.compiler.canonical;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

import org.jbpm.process.instance.KogitoProcessContextImpl;
import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionUtilsTest {

    private static class ServiceExample {

        @SuppressWarnings("unused")
        public int primitiveType(String s, int a) {
            return a;
        }

        @SuppressWarnings("unused")
        public Float primitiveType(String s, Float a) {
            return a;
        }

        public String getStringWithContext(String prefix, KogitoProcessContext context) {
            return prefix + "-" + context.getVariable("boy");
        }

        public String getStringWithContext(KogitoProcessContext context) {
            return (String) context.getVariable("boy");
        }
    }

    @Test
    public void testGetMethod() throws ReflectiveOperationException {
        ServiceExample instance = new ServiceExample();
        Method m = ReflectionUtils
                .getMethod(
                        Thread.currentThread().getContextClassLoader(),
                        ServiceExample.class,
                        "primitiveType",
                        Arrays.asList("String", "Integer"));
        assertThat(m.invoke(instance, "pepe", 2)).isEqualTo(Integer.valueOf(2));
        m = ReflectionUtils
                .getMethod(
                        Thread.currentThread().getContextClassLoader(),
                        ServiceExample.class,
                        "primitiveType",
                        Arrays.asList("String", "Float"));
        assertThat(m.invoke(instance, "pepe", 2.0f)).isEqualTo(Float.valueOf(2.0f));

        KogitoProcessContext context = new KogitoProcessContextImpl(null) {
            @Override
            public Object getVariable(String variableName) {
                return variableName;
            }
        };
        m = ReflectionUtils.getMethod(Thread.currentThread().getContextClassLoader(),
                ServiceExample.class, "getStringWithContext", Arrays.asList("String"));
        assertThat(m.invoke(instance, "dummy", context)).isEqualTo("dummy-boy");

        m = ReflectionUtils.getMethod(Thread.currentThread().getContextClassLoader(),
                ServiceExample.class, "getStringWithContext", Collections.emptyList());
        assertThat(m.invoke(instance, context)).isEqualTo("boy");
    }

}
