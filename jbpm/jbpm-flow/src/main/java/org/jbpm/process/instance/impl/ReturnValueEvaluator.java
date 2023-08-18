package org.jbpm.process.instance.impl;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public interface ReturnValueEvaluator {

    Object evaluate(KogitoProcessContext processContext) throws Exception;
}
