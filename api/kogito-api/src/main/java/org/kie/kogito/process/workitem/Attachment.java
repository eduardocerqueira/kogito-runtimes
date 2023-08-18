package org.kie.kogito.process.workitem;

import java.net.URI;

public class Attachment extends TaskMetaEntity<String, URI> {

    private String name;

    public Attachment(String id, String user) {
        super(id, user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
