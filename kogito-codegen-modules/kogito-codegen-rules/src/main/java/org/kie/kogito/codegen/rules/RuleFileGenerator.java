package org.kie.kogito.codegen.rules;

import org.drools.codegen.common.GeneratedFile;
import org.drools.drl.parser.DroolsError;

public interface RuleFileGenerator {
    String generatedFilePath();

    GeneratedFile generate();

    default boolean validate() {
        return true;
    }

    default DroolsError getError() {
        return null;
    }
}
