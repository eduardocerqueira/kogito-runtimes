package org.kie.kogito.codegen.process.persistence.marshaller;

import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

public interface MarshallerGenerator {

    List<CompilationUnit> generate(String content) throws IOException;
}
