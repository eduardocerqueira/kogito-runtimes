package org.jbpm.bpmn2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImportClassTest extends JbpmBpmn2TestCase {

    @Test
    public void testResourceType() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> createKogitoProcessRuntime("build/sample.bpmn", "build/sample2.bpmn"))
                .withMessageContaining("Process Compilation error HelloService cannot be resolved to a type");
    }

}
