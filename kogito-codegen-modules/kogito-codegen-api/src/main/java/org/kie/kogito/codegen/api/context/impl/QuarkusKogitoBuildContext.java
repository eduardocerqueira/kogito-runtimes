package org.kie.kogito.codegen.api.context.impl;

import org.kie.kogito.codegen.api.di.impl.CDIDependencyInjectionAnnotator;
import org.kie.kogito.codegen.api.rest.impl.CDIRestAnnotator;

public class QuarkusKogitoBuildContext extends AbstractKogitoBuildContext {

    public static final String CONTEXT_NAME = "Quarkus";
    public static final String QUARKUS_REST = "javax.ws.rs.Path";
    public static final String QUARKUS_DI = "javax.inject.Inject";

    protected QuarkusKogitoBuildContext(QuarkusKogitoBuildContextBuilder builder) {
        super(builder, new CDIDependencyInjectionAnnotator(), new CDIRestAnnotator(), CONTEXT_NAME);
    }

    public static Builder builder() {
        return new QuarkusKogitoBuildContextBuilder();
    }

    protected static class QuarkusKogitoBuildContextBuilder extends AbstractBuilder {

        protected QuarkusKogitoBuildContextBuilder() {
        }

        @Override
        public QuarkusKogitoBuildContext build() {
            return new QuarkusKogitoBuildContext(this);
        }

        @Override
        public String toString() {
            return QuarkusKogitoBuildContext.CONTEXT_NAME;
        }
    }
}