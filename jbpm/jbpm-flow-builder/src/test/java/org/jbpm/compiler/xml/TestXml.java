package org.jbpm.compiler.xml;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import org.jbpm.compiler.xml.core.SemanticModules;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.jbpm.test.util.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import org.kie.api.definition.process.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class TestXml extends AbstractBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(TestXml.class);

    @Test
    public void testSimpleXml() throws Exception {
        SemanticModules modules = new SemanticModules();
        modules.addSemanticModule(new ProcessSemanticModule());
        XmlProcessReader reader = new XmlProcessReader(modules, getClass().getClassLoader());
        reader.read(new InputStreamReader(TestXml.class.getResourceAsStream("XmlTest.xml")));
        List<Process> processes = reader.getProcess();
        assertThat(processes).isNotNull().hasSize(1);

        RuleFlowProcess process = (RuleFlowProcess) processes.get(0);
        assertThat(process).isNotNull();

        String output = XmlRuleFlowProcessDumper.INSTANCE.dump(process);
        logger.info(output);
        reader = new XmlProcessReader(new SemanticModules(), getClass().getClassLoader());
        reader.read(new StringReader(output));
    }
}
