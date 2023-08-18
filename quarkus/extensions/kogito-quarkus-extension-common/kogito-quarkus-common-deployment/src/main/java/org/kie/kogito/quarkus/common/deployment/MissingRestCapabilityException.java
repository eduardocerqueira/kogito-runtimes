package org.kie.kogito.quarkus.common.deployment;

public class MissingRestCapabilityException extends RuntimeException {
    public MissingRestCapabilityException() {
        super("No REST capability detected! \n" +
                "Add RestEasy (quarkus-resteasy) and RestEasy Jackson (quarkus-resteasy-jackson) extensions or " +
                "add RestEasy Reactive (quarkus-resteasy-reactive) and RestEasy Reactive Jackson (quarkus-resteasy-reactive-jackson) extensions " +
                "if you want Kogito to generate REST endpoints automatically. \n" +
                "You may also disable automated REST generation by setting `kogito.generate.rest = false`. \n" +
                "You may also override this notice by setting `kogito.generate.rest = true` ");
    }
}
