package org.jbpm.process.builder.dialect;

import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jbpm.process.builder.dialect.java.JavaProcessDialect;
import org.jbpm.process.builder.dialect.mvel.MVELProcessDialect;

public class ProcessDialectRegistry {

    private ProcessDialectRegistry() {

    }

    private static ConcurrentMap<String, ProcessDialect> dialects;

    static {
        dialects = new ConcurrentHashMap<>();
        dialects.put("java", new JavaProcessDialect());
        dialects.put("mvel", new MVELProcessDialect());
        ServiceLoader<ProcessDialectProvider> providers = ServiceLoader.load(ProcessDialectProvider.class);
        providers.forEach(provider -> dialects.put(provider.name(), provider.dialect()));

    }

    public static ProcessDialect getDialect(String dialect) {
        return dialects.get(dialect);
    }

    public static void setDialect(String dialectName, ProcessDialect dialect) {
        dialects.put(dialectName, dialect);
    }

}
