package org.kogito.workitem.rest.decorators;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

import io.vertx.mutiny.ext.web.client.HttpRequest;

public abstract class AbstractParamsDecorator implements ParamsDecorator {

    @Override
    public void decorate(KogitoWorkItem item, Map<String, Object> parameters, HttpRequest<?> request) {
        Iterator<Entry<String, Object>> iter = parameters.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Object> entry = iter.next();
            String key = entry.getKey();
            if (isHeaderParameter(key)) {
                request.putHeader(toHeaderKey(key), entry.getValue().toString());
                iter.remove();
            } else if (isQueryParameter(key)) {
                request.addQueryParam(toQueryKey(key), entry.getValue().toString());
                iter.remove();
            }
        }
    }

    protected String toHeaderKey(String key) {
        return key;
    }

    protected String toQueryKey(String key) {
        return key;
    }

    protected abstract boolean isHeaderParameter(String key);

    protected abstract boolean isQueryParameter(String key);
}
