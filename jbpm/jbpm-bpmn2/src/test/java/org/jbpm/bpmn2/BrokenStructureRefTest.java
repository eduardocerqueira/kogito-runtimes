package org.jbpm.bpmn2;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.jbpm.process.core.impl.XmlProcessDumper;
import org.jbpm.process.core.impl.XmlProcessDumperFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BrokenStructureRefTest {

    @Test
    public void testProcessWithBrokenItemDefinitionUri() throws Exception {
        String inputBpmn = getClass().getResource("/BPMN2-BrokenStructureRef.bpmn2").getPath();
        XmlProcessDumper dumper = XmlProcessDumperFactory.getXmlProcessDumperFactoryService().newXmlProcessDumper();
        assertThat(dumper).isNotNull();
        String processXml = new String(Files.readAllBytes(Paths.get(inputBpmn)));
        assertThat(processXml).isNotNull();
        org.kie.api.definition.process.Process proc = dumper.readProcess(processXml);
        assertThat(proc).isNotNull();
        assertThat(proc.getId()).isEqualTo("BrokenStructureRef");
    }
}
