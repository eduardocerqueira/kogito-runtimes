package org.kie.kogito;

import java.util.Map;

/**
 * To be implemented by classes which can express its internal information as a Map
 */
public interface MapOutput {

    /**
     * Returns class representation as map
     * 
     * @return non null map of data extracted from the class
     */
    default Map<String, Object> toMap() {
        return Models.toMap(this);
    }
}
