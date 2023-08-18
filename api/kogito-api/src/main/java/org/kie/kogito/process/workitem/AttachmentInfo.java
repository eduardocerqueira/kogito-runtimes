package org.kie.kogito.process.workitem;

import java.net.URI;

public class AttachmentInfo {

    private URI uri;
    private String name;

    public AttachmentInfo() {
    }

    public AttachmentInfo(URI uri) {
        this(uri, null);
    }

    public AttachmentInfo(URI uri, String name) {
        this.uri = uri;
        this.name = name;
    }

    public URI getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }
}
