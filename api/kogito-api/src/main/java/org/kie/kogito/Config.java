package org.kie.kogito;

/**
 * Provides general configuration of Kogito application
 */
public interface Config {

    /**
     * Provides instance of requested KogitoConfig or null if not available
     * 
     * @param clazz clazz of the desired KogitoConfig
     * @return
     */
    <T extends KogitoConfig> T get(Class<T> clazz);

    /**
     * Provides access to addons in the application.
     *
     * @return addons available in the application
     */
    default Addons addons() {
        return Addons.EMTPY;
    }

}
