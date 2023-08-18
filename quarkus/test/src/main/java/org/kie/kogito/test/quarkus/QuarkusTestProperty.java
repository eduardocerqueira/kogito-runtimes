package org.kie.kogito.test.quarkus;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
@Target({ FIELD })
public @interface QuarkusTestProperty {

    String name();

    String defaultValue() default "";
}
