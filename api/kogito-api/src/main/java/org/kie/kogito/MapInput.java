package org.kie.kogito;

import java.util.Map;

/**
 * To be implemented by classes which can be populated from a Map
 */
public interface MapInput {

    /**
     * Fills the class with information retrieved from the map
     * 
     * @param params Map containing keys which matches names of fields
     *        in the class
     */
    default MapInput fromMap(Map<String, Object> params) {
        Models.fromMap(this, params);
        return this;
    }

}
