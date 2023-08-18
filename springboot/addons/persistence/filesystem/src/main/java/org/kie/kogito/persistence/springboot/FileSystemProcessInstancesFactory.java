package org.kie.kogito.persistence.springboot;

import org.kie.kogito.persistence.filesystem.AbstractProcessInstancesFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileSystemProcessInstancesFactory extends AbstractProcessInstancesFactory {

    public FileSystemProcessInstancesFactory(@Value("${kogito.persistence.filesystem.path:/tmp}") String path) {
        super(path);
    }

}
