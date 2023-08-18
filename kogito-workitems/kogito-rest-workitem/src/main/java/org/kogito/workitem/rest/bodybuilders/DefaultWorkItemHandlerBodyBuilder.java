package org.kogito.workitem.rest.bodybuilders;

import java.util.Map;

import org.kogito.workitem.rest.RestWorkItemHandler;

public class DefaultWorkItemHandlerBodyBuilder implements RestWorkItemHandlerBodyBuilder {
    @Override
    public Object apply(Map<String, Object> parameters) {
        Object contentData = parameters.get(RestWorkItemHandler.CONTENT_DATA);
        return contentData != null ? contentData : buildFromParams(parameters);
    }

    protected Object buildFromParams(Map<String, Object> parameters) {
        return parameters;
    }
}
