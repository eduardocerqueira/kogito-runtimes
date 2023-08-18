package org.jbpm.integrationtests;

import java.io.Reader;
import java.io.StringReader;

import org.drools.io.ReaderResource;
import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.process.instance.context.variable.VariableScopeInstance;
import org.jbpm.test.util.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import org.kie.api.io.ResourceType;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessEventTest extends AbstractBaseTest {

    @Test
    public void testInternalNodeSignalEvent() {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"flow\" id=\"org.drools.core.event\" package-name=\"org.drools\" version=\"1\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <variables>\n" +
                        "      <variable name=\"MyVar\" >\n" +
                        "        <type name=\"org.jbpm.process.core.datatype.impl.type.StringDataType\" />\n" +
                        "        <value>SomeText</value>\n" +
                        "      </variable>\n" +
                        "    </variables>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <eventNode id=\"2\" name=\"Event\" variableName=\"MyVar\" >\n" +
                        "      <eventFilters>\n" +
                        "        <eventFilter type=\"eventType\" eventType=\"MyEvent\" />\n" +
                        "      </eventFilters>\n" +
                        "    </eventNode>\n" +
                        "    <actionNode id=\"3\" name=\"Signal Event\" >\n" +
                        "      <action type=\"expression\" dialect=\"java\" >kcontext.getProcessInstance().signalEvent(\"MyEvent\", \"MyValue\");</action>\n" +
                        "    </actionNode>\n" +
                        "    <join id=\"4\" name=\"Join\" type=\"1\" />\n" +
                        "    <end id=\"5\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"3\" />\n" +
                        "    <connection from=\"2\" to=\"4\" />\n" +
                        "    <connection from=\"3\" to=\"4\" />\n" +
                        "    <connection from=\"4\" to=\"5\" />\n" +
                        "  </connections>\n" +
                        "\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.core.event");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_COMPLETED);
        assertThat(((VariableScopeInstance) ((org.jbpm.process.instance.ProcessInstance) processInstance).getContextInstance(
                VariableScope.VARIABLE_SCOPE)).getVariable("MyVar")).isEqualTo("MyValue");
    }

    @Test
    public void testProcessInstanceSignalEvent() throws Exception {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"flow\" id=\"org.drools.core.event\" package-name=\"org.drools\" version=\"1\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <variables>\n" +
                        "      <variable name=\"MyVar\" >\n" +
                        "        <type name=\"org.jbpm.process.core.datatype.impl.type.StringDataType\" />\n" +
                        "        <value>SomeText</value>\n" +
                        "      </variable>\n" +
                        "    </variables>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <eventNode id=\"2\" name=\"Event\" variableName=\"MyVar\" >\n" +
                        "      <eventFilters>\n" +
                        "        <eventFilter type=\"eventType\" eventType=\"MyEvent\" />\n" +
                        "      </eventFilters>\n" +
                        "    </eventNode>\n" +
                        "    <join id=\"3\" name=\"Join\" type=\"1\" />\n" +
                        "    <end id=\"4\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"3\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "    <connection from=\"3\" to=\"4\" />\n" +
                        "  </connections>\n" +
                        "\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.core.event");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_ACTIVE);

        processInstance = kruntime.getProcessInstance(processInstance.getStringId());
        processInstance.signalEvent("MyEvent", "MyValue");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_COMPLETED);
        assertThat(((VariableScopeInstance) ((org.jbpm.process.instance.ProcessInstance) processInstance).getContextInstance(
                VariableScope.VARIABLE_SCOPE)).getVariable("MyVar")).isEqualTo("MyValue");
    }

    @Test
    public void testExternalEventCorrelation() throws Exception {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"flow\" id=\"org.drools.core.event\" package-name=\"org.drools\" version=\"1\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <variables>\n" +
                        "      <variable name=\"MyVar\" >\n" +
                        "        <type name=\"org.jbpm.process.core.datatype.impl.type.StringDataType\" />\n" +
                        "        <value>SomeText</value>\n" +
                        "      </variable>\n" +
                        "    </variables>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <eventNode id=\"2\" name=\"Event\" variableName=\"MyVar\" scope=\"external\" >\n" +
                        "      <eventFilters>\n" +
                        "        <eventFilter type=\"eventType\" eventType=\"MyEvent\" />\n" +
                        "      </eventFilters>\n" +
                        "    </eventNode>\n" +
                        "    <join id=\"3\" name=\"Join\" type=\"1\" />\n" +
                        "    <end id=\"4\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"3\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "    <connection from=\"3\" to=\"4\" />\n" +
                        "  </connections>\n" +
                        "\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.core.event");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_ACTIVE);
        assertThat(((VariableScopeInstance) ((org.jbpm.process.instance.ProcessInstance) processInstance).getContextInstance(
                VariableScope.VARIABLE_SCOPE)).getVariable("MyVar")).isEqualTo("SomeText");
        processInstance = kruntime.getProcessInstance(processInstance.getStringId());
        kruntime.signalEvent("MyEvent", "MyValue");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_COMPLETED);
        assertThat(((VariableScopeInstance) ((org.jbpm.process.instance.ProcessInstance) processInstance).getContextInstance(
                VariableScope.VARIABLE_SCOPE)).getVariable("MyVar")).isEqualTo("MyValue");
    }

    @Test
    public void testInternalEventCorrelation() throws Exception {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"flow\" id=\"org.drools.core.event\" package-name=\"org.drools\" version=\"1\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <variables>\n" +
                        "      <variable name=\"MyVar\" >\n" +
                        "        <type name=\"org.jbpm.process.core.datatype.impl.type.StringDataType\" />\n" +
                        "        <value>SomeText</value>\n" +
                        "      </variable>\n" +
                        "    </variables>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <eventNode id=\"2\" name=\"Event\" variableName=\"MyVar\" >\n" +
                        "      <eventFilters>\n" +
                        "        <eventFilter type=\"eventType\" eventType=\"MyEvent\" />\n" +
                        "      </eventFilters>\n" +
                        "    </eventNode>\n" +
                        "    <join id=\"3\" name=\"Join\" type=\"1\" />\n" +
                        "    <end id=\"4\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"3\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "    <connection from=\"3\" to=\"4\" />\n" +
                        "  </connections>\n" +
                        "\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.core.event");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_ACTIVE);
        assertThat(((VariableScopeInstance) ((org.jbpm.process.instance.ProcessInstance) processInstance).getContextInstance(
                VariableScope.VARIABLE_SCOPE)).getVariable("MyVar")).isEqualTo("SomeText");

        processInstance = kruntime.getProcessInstance(processInstance.getStringId());
        kruntime.signalEvent("MyEvent", "MyValue");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_ACTIVE);
    }

    @Test
    public void testInternalNodeSignalCompositeEvent() {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"flow\" id=\"org.drools.core.event\" package-name=\"org.drools\" version=\"1\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <variables>\n" +
                        "      <variable name=\"MyVar\" >\n" +
                        "        <type name=\"org.jbpm.process.core.datatype.impl.type.StringDataType\" />\n" +
                        "        <value>SomeText</value>\n" +
                        "      </variable>\n" +
                        "    </variables>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <composite id=\"2\" name=\"CompositeNode\" >\n" +
                        "      <nodes>\n" +
                        "        <actionNode id=\"1\" name=\"Signal Event\" >\n" +
                        "          <action type=\"expression\" dialect=\"java\" >kcontext.getProcessInstance().signalEvent(\"MyEvent\", \"MyValue\");</action>\n" +
                        "        </actionNode>\n" +
                        "        <eventNode id=\"2\" name=\"Event\" variableName=\"MyVar\" >\n" +
                        "          <eventFilters>\n" +
                        "            <eventFilter type=\"eventType\" eventType=\"MyEvent\" />\n" +
                        "          </eventFilters>\n" +
                        "        </eventNode>\n" +
                        "        <join id=\"3\" name=\"Join\" type=\"1\" />\n" +
                        "      </nodes>\n" +
                        "      <connections>\n" +
                        "        <connection from=\"1\" to=\"3\" />\n" +
                        "        <connection from=\"2\" to=\"3\" />\n" +
                        "      </connections>\n" +
                        "      <in-ports>\n" +
                        "        <in-port type=\"DROOLS_DEFAULT\" nodeId=\"1\" nodeInType=\"DROOLS_DEFAULT\" />\n" +
                        "      </in-ports>\n" +
                        "      <out-ports>\n" +
                        "        <out-port type=\"DROOLS_DEFAULT\" nodeId=\"3\" nodeOutType=\"DROOLS_DEFAULT\" />\n" +
                        "      </out-ports>\n" +
                        "    </composite>\n" +
                        "    <end id=\"3\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"2\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "  </connections>\n" +
                        "\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.core.event");
        assertThat(processInstance.getState()).as("Process did not complete!").isEqualTo(KogitoProcessInstance.STATE_COMPLETED);
        assertThat(((VariableScopeInstance) ((org.jbpm.process.instance.ProcessInstance) processInstance).getContextInstance(
                VariableScope.VARIABLE_SCOPE)).getVariable("MyVar")).isEqualTo("MyValue");
    }

    @Test
    public void testProcessInstanceSignalCompositeEvent() throws Exception {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"flow\" id=\"org.drools.core.event\" package-name=\"org.drools\" version=\"1\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <variables>\n" +
                        "      <variable name=\"MyVar\" >\n" +
                        "        <type name=\"org.jbpm.process.core.datatype.impl.type.StringDataType\" />\n" +
                        "        <value>SomeText</value>\n" +
                        "      </variable>\n" +
                        "    </variables>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <composite id=\"2\" name=\"CompositeNode\" >\n" +
                        "      <nodes>\n" +
                        "        <eventNode id=\"2\" name=\"Event\" variableName=\"MyVar\" >\n" +
                        "          <eventFilters>\n" +
                        "            <eventFilter type=\"eventType\" eventType=\"MyEvent\" />\n" +
                        "          </eventFilters>\n" +
                        "        </eventNode>\n" +
                        "        <join id=\"3\" name=\"Join\" type=\"1\" />\n" +
                        "      </nodes>\n" +
                        "      <connections>\n" +
                        "        <connection from=\"2\" to=\"3\" />\n" +
                        "      </connections>\n" +
                        "      <in-ports>\n" +
                        "        <in-port type=\"DROOLS_DEFAULT\" nodeId=\"3\" nodeInType=\"DROOLS_DEFAULT\" />\n" +
                        "      </in-ports>\n" +
                        "      <out-ports>\n" +
                        "        <out-port type=\"DROOLS_DEFAULT\" nodeId=\"3\" nodeOutType=\"DROOLS_DEFAULT\" />\n" +
                        "      </out-ports>\n" +
                        "    </composite>\n" +
                        "    <end id=\"3\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"2\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "  </connections>\n" +
                        "\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.core.event");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_ACTIVE);

        processInstance = kruntime.getProcessInstance(processInstance.getStringId());
        processInstance.signalEvent("MyEvent", "MyValue");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_COMPLETED);
        assertThat(((VariableScopeInstance) ((org.jbpm.process.instance.ProcessInstance) processInstance).getContextInstance(
                VariableScope.VARIABLE_SCOPE)).getVariable("MyVar")).isEqualTo("MyValue");
    }

    @Test
    public void testExternalCompositeEventCorrelation() throws Exception {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"flow\" id=\"org.drools.core.event\" package-name=\"org.drools\" version=\"1\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <variables>\n" +
                        "      <variable name=\"MyVar\" >\n" +
                        "        <type name=\"org.jbpm.process.core.datatype.impl.type.StringDataType\" />\n" +
                        "        <value>SomeText</value>\n" +
                        "      </variable>\n" +
                        "    </variables>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <composite id=\"2\" name=\"CompositeNode\" >\n" +
                        "      <nodes>\n" +
                        "        <eventNode id=\"2\" name=\"Event\" variableName=\"MyVar\" scope=\"external\" >\n" +
                        "          <eventFilters>\n" +
                        "            <eventFilter type=\"eventType\" eventType=\"MyEvent\" />\n" +
                        "          </eventFilters>\n" +
                        "        </eventNode>\n" +
                        "        <join id=\"3\" name=\"Join\" type=\"1\" />\n" +
                        "      </nodes>\n" +
                        "      <connections>\n" +
                        "        <connection from=\"2\" to=\"3\" />\n" +
                        "      </connections>\n" +
                        "      <in-ports>\n" +
                        "        <in-port type=\"DROOLS_DEFAULT\" nodeId=\"3\" nodeInType=\"DROOLS_DEFAULT\" />\n" +
                        "      </in-ports>\n" +
                        "      <out-ports>\n" +
                        "        <out-port type=\"DROOLS_DEFAULT\" nodeId=\"3\" nodeOutType=\"DROOLS_DEFAULT\" />\n" +
                        "      </out-ports>\n" +
                        "    </composite>\n" +
                        "    <end id=\"3\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"2\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "  </connections>\n" +
                        "\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.core.event");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_ACTIVE);

        processInstance = kruntime.getProcessInstance(processInstance.getStringId());
        kruntime.signalEvent("MyEvent", "MyValue");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_COMPLETED);
        assertThat(((VariableScopeInstance) ((org.jbpm.process.instance.ProcessInstance) processInstance).getContextInstance(
                VariableScope.VARIABLE_SCOPE)).getVariable("MyVar")).isEqualTo("MyValue");
    }

    @Test
    public void testInternalCompositeEventCorrelation() throws Exception {
        Reader source = new StringReader(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
                        "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
                        "         type=\"RuleFlow\" name=\"flow\" id=\"org.drools.core.event\" package-name=\"org.drools\" version=\"1\" >\n" +
                        "\n" +
                        "  <header>\n" +
                        "    <variables>\n" +
                        "      <variable name=\"MyVar\" >\n" +
                        "        <type name=\"org.jbpm.process.core.datatype.impl.type.StringDataType\" />\n" +
                        "        <value>SomeText</value>\n" +
                        "      </variable>\n" +
                        "    </variables>\n" +
                        "  </header>\n" +
                        "\n" +
                        "  <nodes>\n" +
                        "    <start id=\"1\" name=\"Start\" />\n" +
                        "    <composite id=\"2\" name=\"CompositeNode\" >\n" +
                        "      <nodes>\n" +
                        "        <eventNode id=\"2\" name=\"Event\" variableName=\"MyVar\" >\n" +
                        "          <eventFilters>\n" +
                        "            <eventFilter type=\"eventType\" eventType=\"MyEvent\" />\n" +
                        "          </eventFilters>\n" +
                        "        </eventNode>\n" +
                        "        <join id=\"3\" name=\"Join\" type=\"1\" />\n" +
                        "      </nodes>\n" +
                        "      <connections>\n" +
                        "        <connection from=\"2\" to=\"3\" />\n" +
                        "      </connections>\n" +
                        "      <in-ports>\n" +
                        "        <in-port type=\"DROOLS_DEFAULT\" nodeId=\"3\" nodeInType=\"DROOLS_DEFAULT\" />\n" +
                        "      </in-ports>\n" +
                        "      <out-ports>\n" +
                        "        <out-port type=\"DROOLS_DEFAULT\" nodeId=\"3\" nodeOutType=\"DROOLS_DEFAULT\" />\n" +
                        "      </out-ports>\n" +
                        "    </composite>\n" +
                        "    <end id=\"3\" name=\"End\" />\n" +
                        "  </nodes>\n" +
                        "\n" +
                        "  <connections>\n" +
                        "    <connection from=\"1\" to=\"2\" />\n" +
                        "    <connection from=\"2\" to=\"3\" />\n" +
                        "  </connections>\n" +
                        "\n" +
                        "</process>");
        builder.add(new ReaderResource(source), ResourceType.DRF);
        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();

        KogitoProcessInstance processInstance = kruntime.startProcess("org.drools.core.event");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_ACTIVE);

        processInstance = kruntime.getProcessInstance(processInstance.getStringId());
        kruntime.signalEvent("MyEvent", "MyValue");
        assertThat(processInstance.getState()).isEqualTo(KogitoProcessInstance.STATE_ACTIVE);
    }

}
