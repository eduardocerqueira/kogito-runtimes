package org.kogito.workitem.rest.decorators;

public class PrefixParamsDecorator extends AbstractParamsDecorator {

    private static final String HEADER_PREFIX = "HEADER_";
    private static final String QUERY_PREFIX = "QUERY_";

    @Override
    protected boolean isHeaderParameter(String key) {
        return key.startsWith(HEADER_PREFIX);
    }

    @Override
    protected boolean isQueryParameter(String key) {
        return key.startsWith(QUERY_PREFIX);
    }

    @Override
    protected String toHeaderKey(String key) {
        return key.substring(HEADER_PREFIX.length());
    }

    @Override
    protected String toQueryKey(String key) {
        return key.substring(QUERY_PREFIX.length());
    }
}
