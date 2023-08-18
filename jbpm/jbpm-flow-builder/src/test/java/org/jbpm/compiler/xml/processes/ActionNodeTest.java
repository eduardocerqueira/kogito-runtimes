package org.jbpm.compiler.xml.processes;

import java.util.ArrayList;
import java.util.List;

import org.drools.io.ClassPathResource;
import org.jbpm.test.util.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import org.kie.api.io.ResourceType;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

import static org.assertj.core.api.Assertions.assertThat;

public class ActionNodeTest extends AbstractBaseTest {

    @Test
    public void testSingleActionNode() throws Exception {
        builder.add(new ClassPathResource("ActionNodeTest.xml", ActionNodeTest.class), ResourceType.DRF);

        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();
        List<String> list = new ArrayList<String>();
        kruntime.getKieSession().setGlobal("list", list);

        kruntime.startProcess("process name");

        assertThat(list).hasSize(1);
        assertThat(list.get(0)).isEqualTo("action node was here");
    }
}
