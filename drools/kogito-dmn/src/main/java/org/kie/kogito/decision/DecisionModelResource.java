package org.kie.kogito.decision;

import java.util.function.Supplier;

import org.kie.kogito.KogitoGAV;

public interface DecisionModelResource extends Supplier<String> {

    KogitoGAV getGav();

    String getNamespace();

    String getModelName();

    DecisionModelMetadata getModelMetadata();
}
