package org.jbpm.compiler.xml;

import java.io.StringReader;
import java.util.List;

import org.jbpm.compiler.xml.compiler.SemanticKnowledgeBuilderConfigurationImpl;
import org.jbpm.compiler.xml.core.SemanticModules;
import org.jbpm.process.core.impl.XmlProcessDumper;
import org.kie.api.definition.process.Process;
import org.kie.api.definition.process.WorkflowProcess;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcess;

public class XmlRuleFlowProcessDumper extends XmlWorkflowProcessDumper implements XmlProcessDumper {

    public static final XmlRuleFlowProcessDumper INSTANCE = new XmlRuleFlowProcessDumper();

    public XmlRuleFlowProcessDumper() {
        super(
                KogitoWorkflowProcess.RULEFLOW_TYPE,
                "http://drools.org/drools-5.0/process",
                "drools-processes-5.0.xsd",
                new ProcessSemanticModule());
    }

    public String dumpProcess(Process process) {
        return dump((WorkflowProcess) process, false);
    }

    @Override
    public Process readProcess(String processXml) {
        SemanticKnowledgeBuilderConfigurationImpl configuration = (SemanticKnowledgeBuilderConfigurationImpl) KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();
        SemanticModules modules = configuration.getSemanticModules();
        modules.addSemanticModule(new ProcessSemanticModule());
        XmlProcessReader xmlReader = new XmlProcessReader(modules, Thread.currentThread().getContextClassLoader());
        try {
            List<Process> processes = xmlReader.read(new StringReader(processXml));
            return processes.get(0);
        } catch (Exception t) {
            t.printStackTrace();
            return null;
        }
    }

}
