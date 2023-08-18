package org.jbpm.process.builder;

import org.drools.drl.ast.descr.ProcessDescr;
import org.kie.api.definition.process.Node;
import org.kie.api.definition.process.Process;

public interface ProcessNodeBuilder {
    public void build(Process process, ProcessDescr processDescr, ProcessBuildContext context, Node node);
}
