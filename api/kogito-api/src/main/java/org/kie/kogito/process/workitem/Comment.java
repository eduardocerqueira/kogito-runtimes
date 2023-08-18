package org.kie.kogito.process.workitem;

public class Comment extends TaskMetaEntity<String, String> {

    public Comment(String id, String user) {
        super(id, user);
    }
}
