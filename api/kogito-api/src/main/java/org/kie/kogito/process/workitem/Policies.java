package org.kie.kogito.process.workitem;

import java.util.Collection;
import java.util.Collections;

import org.kie.kogito.auth.IdentityProviders;
import org.kie.kogito.auth.SecurityPolicy;

public class Policies {

    @SuppressWarnings("unchecked")
    public static <T> Policy<T>[] of(String user, Collection<String> roles) {
        return user == null ? new Policy[0] : new Policy[] { SecurityPolicy.of(IdentityProviders.of(user, roles)) };
    }

    public static <T> Policy<T>[] of(String user) {
        return of(user, Collections.emptyList());
    }

    private Policies() {
    }

}
