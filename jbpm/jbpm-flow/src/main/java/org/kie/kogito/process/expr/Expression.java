package org.kie.kogito.process.expr;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;

public interface Expression {

    <T> T eval(Object target, Class<T> returnClass, KogitoProcessContext context);

    boolean isValid();

    Exception validationError();

    void assign(Object target, Object value, KogitoProcessContext context);

    String asString();

    String lang();
}
