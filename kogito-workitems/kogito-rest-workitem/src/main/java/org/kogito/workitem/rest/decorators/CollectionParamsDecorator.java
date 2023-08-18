package org.kogito.workitem.rest.decorators;

import java.util.Collection;

public class CollectionParamsDecorator extends AbstractParamsDecorator {

    private final Collection<String> headerParams;
    private final Collection<String> queryParams;

    public CollectionParamsDecorator(Collection<String> headerParams, Collection<String> queryParams) {
        this.headerParams = headerParams;
        this.queryParams = queryParams;
    }

    @Override
    protected boolean isHeaderParameter(String key) {
        return headerParams.contains(key);
    }

    @Override
    protected boolean isQueryParameter(String key) {
        return queryParams.contains(key);
    }
}
