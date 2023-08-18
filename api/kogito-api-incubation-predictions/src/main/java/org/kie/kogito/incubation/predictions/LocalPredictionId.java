package org.kie.kogito.incubation.predictions;

import org.kie.kogito.incubation.common.Id;
import org.kie.kogito.incubation.common.LocalId;
import org.kie.kogito.incubation.common.LocalUri;
import org.kie.kogito.incubation.common.LocalUriId;

public class LocalPredictionId extends LocalUriId implements Id {
    public static final String PREFIX = "predictions";

    private final String fileName;
    private final String name;

    public LocalPredictionId(String fileName, String name) {
        super(LocalUri.Root.append(PREFIX).append(name));
        this.fileName = fileName;
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public String name() {
        return name;
    }

    @Override
    public LocalId toLocalId() {
        return this;
    }

}
