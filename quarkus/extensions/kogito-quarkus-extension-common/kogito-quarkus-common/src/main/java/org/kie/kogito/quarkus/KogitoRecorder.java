package org.kie.kogito.quarkus;

import java.util.function.Supplier;

import org.kie.kogito.KogitoGAV;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class KogitoRecorder {

    public Supplier<KogitoGAV> kogitoGAVSupplier(String groupId, String artifactId, String version) {
        return () -> new KogitoGAV(groupId, artifactId, version);
    }

}
