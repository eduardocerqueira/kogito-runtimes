package org.kie.kogito.serverless.workflow.utils;

import java.util.Map;
import java.util.function.Function;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public interface KogitoProcessContextResolverExtension {
    Map<String, Function<KogitoProcessContext, Object>> getKogitoProcessContextResolver();
}
