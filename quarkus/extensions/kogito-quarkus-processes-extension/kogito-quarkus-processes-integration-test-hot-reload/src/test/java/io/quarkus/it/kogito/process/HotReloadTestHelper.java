package io.quarkus.it.kogito.process;

import javax.enterprise.context.ApplicationScoped;

/**
 * HotReloadTestHelper
 */
@ApplicationScoped
public class HotReloadTestHelper {

    public String toUpper(String text) {
        return text.toUpperCase();
    }

    public String toLower(String text) {
        return text.toLowerCase();
    }
}
