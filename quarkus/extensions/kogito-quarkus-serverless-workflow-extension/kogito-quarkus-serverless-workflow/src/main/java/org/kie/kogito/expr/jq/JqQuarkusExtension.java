package org.kie.kogito.expr.jq;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import io.quarkus.runtime.Startup;

import net.thisptr.jackson.jq.Scope;

@Startup
public class JqQuarkusExtension {
    @Inject
    Scope scope;

    @PostConstruct
    void init() {
        JqExpressionHandler.setScopeSupplier(() -> scope);
    }
}
