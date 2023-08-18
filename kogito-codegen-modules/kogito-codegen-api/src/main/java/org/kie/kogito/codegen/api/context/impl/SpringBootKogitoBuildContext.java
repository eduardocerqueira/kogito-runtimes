package org.kie.kogito.codegen.api.context.impl;

import org.kie.kogito.codegen.api.di.impl.SpringDependencyInjectionAnnotator;
import org.kie.kogito.codegen.api.rest.impl.SpringRestAnnotator;

public class SpringBootKogitoBuildContext extends AbstractKogitoBuildContext {

    public static final String CONTEXT_NAME = "Spring";
    public static final String SPRING_REST = "org.springframework.web.bind.annotation.RestController";
    public static final String SPRING_DI = "org.springframework.beans.factory.annotation.Autowired";

    protected SpringBootKogitoBuildContext(SpringBootKogitoBuildContextBuilder builder) {
        super(builder, new SpringDependencyInjectionAnnotator(), new SpringRestAnnotator(), CONTEXT_NAME);
    }

    public static Builder builder() {
        return new SpringBootKogitoBuildContextBuilder();
    }

    protected static class SpringBootKogitoBuildContextBuilder extends AbstractBuilder {

        protected SpringBootKogitoBuildContextBuilder() {
        }

        @Override
        public SpringBootKogitoBuildContext build() {
            return new SpringBootKogitoBuildContext(this);
        }

        @Override
        public String toString() {
            return SpringBootKogitoBuildContext.CONTEXT_NAME;
        }
    }
}