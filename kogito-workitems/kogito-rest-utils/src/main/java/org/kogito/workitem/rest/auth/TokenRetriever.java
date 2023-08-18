package org.kogito.workitem.rest.auth;

import java.util.Map;

public interface TokenRetriever {
    String getToken(Map<String, Object> parameters);
}
