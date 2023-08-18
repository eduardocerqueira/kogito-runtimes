package org.kie.kogito.addon.source.files;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public final class SourceFilesProviderProducer {

    SourceFilesProviderProducer() {
    }

    @Produces
    @Default
    @ApplicationScoped
    public SourceFilesProviderImpl sourceFilesProvider() {
        return new SourceFilesProviderImpl();
    }
}
