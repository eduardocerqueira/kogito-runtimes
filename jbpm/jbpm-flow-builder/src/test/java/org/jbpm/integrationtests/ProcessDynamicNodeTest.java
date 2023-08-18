package org.jbpm.integrationtests;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.io.ReaderResource;
import org.jbpm.integrationtests.handler.TestWorkItemHandler;
import org.jbpm.test.util.AbstractBaseTest;
import org.jbpm.workflow.instance.node.DynamicNodeInstance;
import org.jbpm.workflow.instance.node.DynamicUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.io.ResourceFactory;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkflowProcessInstance;
import org.kie.kogito.logger.KogitoRuntimeLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessDynamicNodeTest extends AbstractBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ProcessDynamicNodeTest.class);

    @Test
    @Disabled("Not done yet")
    public void TODOtestDynamicActions() {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"ruleflow\" id=\"org.drools.dynamic\" package-name=\"org.drools\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <globals>\n" +
                        "      <global identifier=\"list\" type=\"java.util.List\" />\n" +
                        "    </globals>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <dynamic id=\"2\" name=\"DynamicNode\" >\n" +
                        "      <nodes>\n" +
                        "        <actionNode id=\"1\" name=\"Action1\" >\n" +
                        "          <action type=\"expression\" dialect=\"mvel\" >System.out.println(\"Action1\");\n" +
                        "list.add(\"Action1\");</action>\n" +
                        "        </actionNode>\n" +
                        "        <actionNode id=\"2\" name=\"Action2\" >\n" +
                        "          <action type=\"expression\" dialect=\"mvel\" >System.out.println(\"Action2\");\n" +
                        "list.add(\"Action2\");</action>\n" +
                        "        </actionNode>\n" +
                        "        <actionNode id=\"3\" name=\"Action3\" >\n" +
                        "          <action type=\"expression\" dialect=\"mvel\" >System.out.println(\"Action3\");\n" +
                        "list.add(\"Action3\");</action>\n" +
                        "        </actionNode>\n" +
                        "      </nodes>\n" +
                        "      <connections>\n" +
                        "        <connection from=\"1\" to=\"3\" />\n" +
                        "      </connections>\n" +
                        "      <in-ports/>\n" +
                        "      <out-ports/>\n" +
                        "    </dynamic>\n" +
                        "    <actionNode id=\"3\" name=\"Action4\" >\n" +
                        "      <action type=\"expression\" dialect=\"mvel\" >System.out.println(\"Action4\");\n" +
                        "list.add(\"Action4\");</action>\n" +
                        "    </actionNode>\n" +
                        "    <end id=\"4\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"2\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "    <connection from=\"3\" to=\"4\" />\n" +
                        "  </connections>\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        for (KnowledgeBuilderError error : builder.getErrors()) {
            logger.error(error.toString());
        }

        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();
        List<String> list = new ArrayList<String>();
        kruntime.getKieSession().setGlobal("list", list);
        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.dynamic");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_COMPLETED);
        assertThat(list).hasSize(4);
    }

    @Test
    @Disabled("Not done yet")
    public void TODOtestDynamicAsyncActions() {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"ruleflow\" id=\"org.drools.dynamic\" package-name=\"org.drools\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <globals>\n" +
                        "      <global identifier=\"list\" type=\"java.util.List\" />\n" +
                        "    </globals>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <dynamic id=\"2\" name=\"DynamicNode\" >\n" +
                        "      <nodes>\n" +
                        "        <workItem id=\"1\" name=\"Work\" >\n" +
                        "          <work name=\"Work\" />\n" +
                        "        </workItem>\n" +
                        "        <actionNode id=\"2\" name=\"Action2\" >\n" +
                        "          <action type=\"expression\" dialect=\"mvel\" >System.out.println(\"Action2\");\n" +
                        "list.add(\"Action2\");</action>\n" +
                        "        </actionNode>\n" +
                        "        <actionNode id=\"3\" name=\"Action3\" >\n" +
                        "          <action type=\"expression\" dialect=\"mvel\" >System.out.println(\"Action3\");\n" +
                        "list.add(\"Action3\");</action>\n" +
                        "        </actionNode>\n" +
                        "      </nodes>\n" +
                        "      <connections>\n" +
                        "        <connection from=\"1\" to=\"3\" />\n" +
                        "      </connections>\n" +
                        "      <in-ports/>\n" +
                        "      <out-ports/>\n" +
                        "    </dynamic>\n" +
                        "    <actionNode id=\"3\" name=\"Action4\" >\n" +
                        "      <action type=\"expression\" dialect=\"mvel\" >System.out.println(\"Action4\");\n" +
                        "list.add(\"Action4\");</action>\n" +
                        "    </actionNode>\n" +
                        "    <end id=\"4\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"2\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "    <connection from=\"3\" to=\"4\" />\n" +
                        "  </connections>\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        for (KnowledgeBuilderError error : builder.getErrors()) {
            logger.error(error.toString());
        }

        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        List<String> list = new ArrayList<String>();
        kruntime.getKieSession().setGlobal("list", list);
        TestWorkItemHandler testHandler = new TestWorkItemHandler();
        kruntime.getKogitoWorkItemManager().registerWorkItemHandler("Work", testHandler);
        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.dynamic");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_ACTIVE);
        assertThat(list).hasSize(1);
        KogitoWorkItem workItem = testHandler.getWorkItem();
        assertThat(workItem).isNotNull();
        kruntime.getKogitoWorkItemManager().completeWorkItem(workItem.getStringId(), null);
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_COMPLETED);
        assertThat(list).hasSize(3);
    }

    @Test
    public void testAddDynamicWorkItem() {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"ruleflow\" id=\"org.drools.dynamic\" package-name=\"org.drools\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <dynamic id=\"2\" name=\"DynamicNode\" >\n" +
                        "      <completionCondition>autocomplete</completionCondition>\n" +
                        "      <nodes>\n" +
                        "        <actionNode id=\"1\" name=\"Action\" >\n" +
                        "          <action type=\"expression\" dialect=\"java\" >System.out.println(\"Action\");</action>\n" +
                        "        </actionNode>\n" +
                        "      </nodes>\n" +
                        "      <connections>\n" +
                        "      </connections>\n" +
                        "      <in-ports/>\n" +
                        "      <out-ports/>\n" +
                        "    </dynamic>\n" +
                        "    <end id=\"3\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"2\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "  </connections>\n" +
                        "</process>");
        builder.add(ResourceFactory.newReaderResource(source), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();
        KieRuntimeLogger logger = KogitoRuntimeLoggerFactory.newFileLogger(kruntime.getKieSession(), "test");
        TestWorkItemHandler handler = new TestWorkItemHandler();
        kruntime.getKogitoWorkItemManager().registerWorkItemHandler("Human Task", handler);
        // start a new process instance
        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.dynamic");
        DynamicNodeInstance dynamicContext = (DynamicNodeInstance) ((KogitoWorkflowProcessInstance) processInstance).getNodeInstances().iterator().next();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("TaskName", "Dynamic Task");
        assertThat(handler.getWorkItem()).isNull();
        assertThat(dynamicContext.getNodeInstances()).isEmpty();
        DynamicUtils.addDynamicWorkItem(dynamicContext, kruntime.getKieRuntime(), "Human Task", parameters);
        assertThat(handler.getWorkItem()).isNotNull();
        assertThat(dynamicContext.getNodeInstances()).hasSize(1);
        logger.close();
    }

    @Test
    public void testAddDynamicSubProcess() {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"ruleflow\" id=\"org.drools.dynamic\" package-name=\"org.drools\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <dynamic id=\"2\" name=\"DynamicNode\" >\n" +
                        "      <completionCondition>autocomplete</completionCondition>\n" +
                        "      <nodes>\n" +
                        "        <actionNode id=\"1\" name=\"Action\" >\n" +
                        "          <action type=\"expression\" dialect=\"java\" >System.out.println(\"Action\");</action>\n" +
                        "        </actionNode>\n" +
                        "      </nodes>\n" +
                        "      <connections>\n" +
                        "      </connections>\n" +
                        "      <in-ports/>\n" +
                        "      <out-ports/>\n" +
                        "    </dynamic>\n" +
                        "    <end id=\"3\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"2\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "  </connections>\n" +
                        "</process>");
        Reader source2 = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"subflow\" id=\"org.drools.subflow\" package-name=\"org.drools\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <variables>\n" +
                        "      <variable name=\"x\" >\n" +
                        "        <type name=\"org.jbpm.process.core.datatype.impl.type.StringDataType\" />\n" +
                        "        <value>SomeText</value>\n" +
                        "      </variable>\n" +
                        "    </variables>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <actionNode id=\"2\" name=\"Action\" >\n" +
                        "      <action type=\"expression\" dialect=\"java\" >System.out.println(x);</action>\n" +
                        "    </actionNode>\n" +
                        "    <workItem id=\"3\" name=\"Work\" >\n" +
                        "      <work name=\"Human Task\" />\n" +
                        "    </workItem>\n" +
                        "    <end id=\"4\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"2\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "    <connection from=\"3\" to=\"4\" />\n" +
                        "  </connections>\n" +
                        "</process>");
        builder.add(ResourceFactory.newReaderResource(source), ResourceType.DRF);
        builder.add(ResourceFactory.newReaderResource(source2), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();
        KieRuntimeLogger logger = KogitoRuntimeLoggerFactory.newFileLogger(kruntime.getKieSession(), "test");
        TestWorkItemHandler handler = new TestWorkItemHandler();
        kruntime.getKogitoWorkItemManager().registerWorkItemHandler("Human Task", handler);
        // start a new process instance
        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.dynamic");
        DynamicNodeInstance dynamicContext = (DynamicNodeInstance) ((KogitoWorkflowProcessInstance) processInstance).getNodeInstances().iterator().next();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("x", "NewValue");
        assertThat(handler.getWorkItem()).isNull();
        assertThat(dynamicContext.getNodeInstances()).isEmpty();
        DynamicUtils.addDynamicSubProcess(dynamicContext, kruntime.getKieRuntime(), "org.drools.subflow", parameters);
        assertThat(handler.getWorkItem()).isNotNull();
        assertThat(dynamicContext.getNodeInstances()).hasSize(1);
        logger.close();
    }

}
