package org.kie.kogito.serverless.workflow.io;

import java.io.InputStream;
import java.net.URI;

public interface URIContentLoader {

    URI uri();

    InputStream getInputStream();

    URIContentLoaderType type();
}
