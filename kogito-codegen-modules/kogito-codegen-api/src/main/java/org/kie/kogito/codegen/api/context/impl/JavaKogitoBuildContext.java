package org.kie.kogito.codegen.api.context.impl;

public class JavaKogitoBuildContext extends AbstractKogitoBuildContext {

    public static final String CONTEXT_NAME = "Java";

    protected JavaKogitoBuildContext(JavaKogitoBuildContextBuilder builder) {
        super(builder, null, null, CONTEXT_NAME);
    }

    public static Builder builder() {
        return new JavaKogitoBuildContextBuilder();
    }

    protected static class JavaKogitoBuildContextBuilder extends AbstractBuilder {

        protected JavaKogitoBuildContextBuilder() {
        }

        @Override
        public JavaKogitoBuildContext build() {
            return new JavaKogitoBuildContext(this);
        }

        @Override
        public String toString() {
            return JavaKogitoBuildContext.CONTEXT_NAME;
        }
    }
}