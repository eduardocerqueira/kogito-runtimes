package org.kie.kogito.test.resources;

/**
 * Test resource for kogito tests.
 * 
 */
public interface TestResource {

    /**
     * @return the resource name.
     */
    String getResourceName();

    /**
     * Start the test resource.
     */
    void start();

    /**
     * Stop the test resource.
     */
    void stop();

    /**
     * @return the exposed mapped port.
     */
    int getMappedPort();

}
