package org.kie.kogito.jackson.utils;

import com.fasterxml.jackson.databind.node.NullNode;

class ListenerAwareUtils {

    static Object handleNull(Object value) {
        return value == null ? NullNode.instance : value;
    }

    private ListenerAwareUtils() {
    }
}
