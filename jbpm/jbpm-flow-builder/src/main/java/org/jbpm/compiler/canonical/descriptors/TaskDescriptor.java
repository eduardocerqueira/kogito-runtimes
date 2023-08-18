package org.jbpm.compiler.canonical.descriptors;

import com.github.javaparser.ast.CompilationUnit;

public interface TaskDescriptor {

    String KEY_WORKITEM_TYPE = "Type";
    String KEY_WORKITEM_INTERFACE = "Interface";
    String KEY_WORKITEM_OPERATION = "Operation";
    String KEY_SERVICE_IMPL = "implementation";
    String DEFAULT_SERVICE_IMPL = "Java";

    String getName();

    String getType();

    CompilationUnit generateHandlerClassForService();

}
