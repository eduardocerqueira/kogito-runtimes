package org.kie.kogito.persistence.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.kogito.persistence.filesystem.AbstractProcessInstancesFactory;

@ApplicationScoped
public class FileSystemProcessInstancesFactory extends AbstractProcessInstancesFactory {

    public FileSystemProcessInstancesFactory() {
        super(null);
    }

    @Inject
    public FileSystemProcessInstancesFactory(@ConfigProperty(name = "kogito.persistence.filesystem.path", defaultValue = "/tmp") String path) {
        super(path);
    }
}
