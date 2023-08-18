package org.kie.kogito.process;

import java.util.Collection;

import org.kie.kogito.KogitoEngine;
import org.kie.kogito.Model;

public interface Processes extends KogitoEngine {

    Process<? extends Model> processById(String processId);

    Collection<String> processIds();

    default void activate() {

    }

    default void deactivate() {

    }
}
