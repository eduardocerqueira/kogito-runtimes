package org.kie.kogito.integrationtests;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class MyMathUtils {

    public static double cos(double x) {
        return Math.cos(x);
    }
}
