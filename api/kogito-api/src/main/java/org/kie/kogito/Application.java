package org.kie.kogito;

import org.kie.kogito.uow.UnitOfWorkManager;

/**
 * Entry point for accessing business automation components
 * such as processes, rules, decisions, etc.
 * <p>
 * It should be considered as singleton kind of object that can be safely
 * used across entire application.
 */
public interface Application {

    /**
     * Returns configuration of the application
     * 
     * @return current configuration
     */
    Config config();

    /**
     * Returns the desired KogitoEngine impl or null if not found
     * 
     * @param clazz of the desired KogitoEngine
     * @return
     */

    <T extends KogitoEngine> T get(Class<T> clazz);

    /**
     * Returns unit of work manager that allows to control execution within the application
     * 
     * @return non null unit of work manager
     */
    UnitOfWorkManager unitOfWorkManager();
}
