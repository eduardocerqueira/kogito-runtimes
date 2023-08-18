package org.kie.kogito.process.version;

import org.kie.kogito.KogitoGAV;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessVersionResolver;

public class ProjectVersionProcessVersionResolver implements ProcessVersionResolver {

    private KogitoGAV kogitoGAV;

    public ProjectVersionProcessVersionResolver(KogitoGAV kogitoGAV) {
        this.kogitoGAV = kogitoGAV;
    }

    @Override
    public String apply(Process process) {
        return kogitoGAV.getVersion();
    }
}
