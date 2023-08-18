package org.kie.kogito.persistence.filesystem;

import java.nio.file.Paths;

import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstancesFactory;

public abstract class AbstractProcessInstancesFactory implements ProcessInstancesFactory {

    private final String path;

    public AbstractProcessInstancesFactory(String path) {
        this.path = path;
    }

    public FileSystemProcessInstances createProcessInstances(Process<?> process) {
        return new FileSystemProcessInstances(process, Paths.get(path));
    }

}
