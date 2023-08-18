package org.jbpm.process.instance.impl;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public interface Action {

    void execute(KogitoProcessContext context) throws Exception;

}
