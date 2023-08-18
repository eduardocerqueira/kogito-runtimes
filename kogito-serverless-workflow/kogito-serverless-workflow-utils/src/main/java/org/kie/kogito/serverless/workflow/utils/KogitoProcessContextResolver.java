package org.kie.kogito.serverless.workflow.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public class KogitoProcessContextResolver {

    public static final String FOR_EACH_OUTPUT_VARIABLE = "_foreach_out_eval";
    public static final String FOR_EACH_PREV_ACTION_RESULT = "prevActionResult";

    private static KogitoProcessContextResolver instance = new KogitoProcessContextResolver();

    public static KogitoProcessContextResolver get() {
        return instance;
    }

    private Map<String, Function<KogitoProcessContext, Object>> methods;

    public KogitoProcessContextResolver() {
        methods = new HashMap<>();
        methods.put("instanceId", k -> k.getProcessInstance().getId());
        methods.put("id", k -> k.getProcessInstance().getProcessId());
        methods.put("name", k -> k.getProcessInstance().getProcessName());
        methods.put("headers", k -> k.getHeaders().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().collect(Collectors.joining()))));
        methods.put(FOR_EACH_PREV_ACTION_RESULT, k -> k.getVariable(FOR_EACH_OUTPUT_VARIABLE));
        ServiceLoader.load(KogitoProcessContextResolverExtension.class).forEach(resolver -> methods.putAll(resolver.getKogitoProcessContextResolver()));
    }

    public Object readKey(KogitoProcessContext context, String key) {
        Function<KogitoProcessContext, Object> m = methods.get(key);
        if (m == null) {
            throw new IllegalArgumentException("Cannot find key " + key + " in context");
        }
        return m.apply(context);
    }
}
