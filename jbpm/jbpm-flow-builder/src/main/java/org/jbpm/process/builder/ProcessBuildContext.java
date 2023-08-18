package org.jbpm.process.builder;

import org.drools.base.definitions.InternalKnowledgePackage;
import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.drools.compiler.compiler.Dialect;
import org.drools.compiler.compiler.DialectCompiletimeRegistry;
import org.drools.compiler.rule.builder.PackageBuildContext;
import org.drools.drl.ast.descr.BaseDescr;
import org.drools.drl.ast.descr.ProcessDescr;
import org.kie.api.definition.process.Process;

public class ProcessBuildContext extends PackageBuildContext {

    private Process process;
    private ProcessDescr processDescr;
    private DialectCompiletimeRegistry dialectRegistry;

    public ProcessBuildContext(final KnowledgeBuilderImpl assemblerContext,
            final InternalKnowledgePackage pkg,
            final Process process,
            final BaseDescr processDescr,
            final DialectCompiletimeRegistry dialectRegistry,
            final Dialect defaultDialect) {
        this.process = process;
        this.processDescr = (ProcessDescr) processDescr;
        this.dialectRegistry = dialectRegistry;
        initContext(assemblerContext,
                pkg,
                processDescr,
                dialectRegistry,
                defaultDialect,
                null);

    }

    public ProcessDescr getProcessDescr() {
        return processDescr;
    }

    public void setProcessDescr(ProcessDescr processDescr) {
        this.processDescr = processDescr;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    @Override
    public DialectCompiletimeRegistry getDialectRegistry() {
        return dialectRegistry;
    }

}
