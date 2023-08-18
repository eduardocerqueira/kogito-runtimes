package org.kie.kogito.codegen.api;

import org.drools.codegen.common.GeneratedFile;
import org.drools.codegen.common.GeneratedFileType;

public interface ConfigGenerator {

    GeneratedFileType APPLICATION_CONFIG_TYPE = GeneratedFileType.of("APPLICATION_CONFIG", GeneratedFileType.Category.SOURCE);

    String configClassName();

    GeneratedFile generate();
}