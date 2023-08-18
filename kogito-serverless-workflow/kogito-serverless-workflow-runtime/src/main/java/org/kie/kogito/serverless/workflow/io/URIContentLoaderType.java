package org.kie.kogito.serverless.workflow.io;

import java.net.URI;

public enum URIContentLoaderType {
    CLASSPATH,
    FILE,
    HTTP;

    public static URIContentLoaderType from(URI uri) {
        switch (uri.getScheme().toLowerCase()) {
            case "file":
                return FILE;
            case "classpath":
                return CLASSPATH;
            case "http":
            case "https":
                return HTTP;
            default:
                throw new IllegalArgumentException("Unrecognized uri protocol " + uri);
        }
    }
}
