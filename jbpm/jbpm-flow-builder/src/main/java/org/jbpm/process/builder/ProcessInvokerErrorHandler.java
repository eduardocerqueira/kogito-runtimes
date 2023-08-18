package org.jbpm.process.builder;

import org.drools.drl.ast.descr.BaseDescr;
import org.kie.api.definition.process.Process;

public class ProcessInvokerErrorHandler extends ProcessErrorHandler {

    public ProcessInvokerErrorHandler(final BaseDescr processDescr,
            final Process process,
            final String message) {
        super(processDescr,
                process,
                message);
    }
}
