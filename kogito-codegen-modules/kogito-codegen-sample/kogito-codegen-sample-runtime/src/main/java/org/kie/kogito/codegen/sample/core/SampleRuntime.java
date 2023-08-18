package org.kie.kogito.codegen.sample.core;

import org.kie.kogito.KogitoEngine;

public interface SampleRuntime extends KogitoEngine {

    SampleModel getModel(String name);

}