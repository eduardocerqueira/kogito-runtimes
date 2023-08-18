package org.jbpm.process.core.correlation;

import java.io.Serializable;
import java.util.function.Function;

public interface CorrelationExpressionEvaluator extends Serializable {

    Object eval(Object event);

    Object eval(Function<String, Object> resolver);
}