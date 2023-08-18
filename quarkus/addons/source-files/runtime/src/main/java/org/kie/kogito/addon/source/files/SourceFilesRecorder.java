package org.kie.kogito.addon.source.files;

import javax.enterprise.inject.spi.CDI;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class SourceFilesRecorder {

    public void addSourceFile(String id, SourceFile sourceFile) {
        CDI.current().select(SourceFilesProviderImpl.class).get().addSourceFile(id, sourceFile);
    }
}
