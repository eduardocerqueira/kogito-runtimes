package org.kogito.workitem.rest.pathresolvers;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultPathParamResolver implements PathParamResolver {

    @Override
    public String apply(String endPoint, Map<String, Object> parameters) {
        Set<String> toRemove = new HashSet<>();
        int start = endPoint.indexOf('{');
        if (start == -1) {
            return endPoint;
        }
        StringBuilder sb = new StringBuilder(endPoint);
        while (start != -1) {
            int end = sb.indexOf("}", start);
            if (end == -1) {
                throw new IllegalArgumentException("malformed endpoint should contain enclosing '}' " + endPoint);
            }
            final String key = sb.substring(start + 1, end);
            final Object value = parameters.get(key);
            if (value == null) {
                throw new IllegalArgumentException("missing parameter " + key);
            }
            toRemove.add(key);
            sb.replace(start, end + 1, URLEncoder.encode(value.toString(), Charset.defaultCharset()));
            start = sb.indexOf("{");
        }
        parameters.keySet().removeAll(toRemove);
        return sb.toString();
    }
}
