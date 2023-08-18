package org.kogito.workitem.rest.pathresolvers;

import java.util.Map;
import java.util.function.BiFunction;

public interface PathParamResolver extends BiFunction<String, Map<String, Object>, String> {
}
