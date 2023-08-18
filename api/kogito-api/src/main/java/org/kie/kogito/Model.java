package org.kie.kogito;

import java.util.Map;

/**
 * Represents data model type of objects that are usually descriptor of data holders.
 *
 */
public interface Model extends MapInput, MapOutput {

    default void update(Map<String, Object> params) {
        Models.fromMap(this, params);
    }
}
