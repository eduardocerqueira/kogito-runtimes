package org.kie.kogito;

import java.util.Map;

/**
 * To be implemented by classes which can be populated from a Map
 */
public interface MapInputId {

    /**
     * Fills the class with information retrieved from the map
     * 
     * @param params Map containing keys which matches names of fields
     *        in the class
     */
    default void fromMap(String id, Map<String, Object> params) {
        Models.fromMap(this, id, params);
    }

}
