package org.jbpm.bpmn2.xml;

import org.jbpm.compiler.xml.compiler.XmlDumper;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.StateNode;
import org.xml.sax.Attributes;

public class StateNodeHandler extends AbstractNodeHandler {

    protected Node createNode(Attributes attrs) {
        throw new IllegalArgumentException("Reading in should be handled by intermediate catch event handler");
    }

    @SuppressWarnings("unchecked")
    public Class generateNodeFor() {
        return StateNode.class;
    }

    public void writeNode(Node node, StringBuilder xmlDump, int metaDataType) {
        StateNode stateNode = (StateNode) node;
        String condition = (String) stateNode.getMetaData("Condition");
        writeNode("intermediateCatchEvent", stateNode, xmlDump, metaDataType);
        xmlDump.append(">" + EOL);
        writeExtensionElements(node, xmlDump);
        xmlDump.append("      <conditionalEventDefinition>" + EOL);
        xmlDump.append("        <condition xsi:type=\"tFormalExpression\" language=\"" + XmlBPMNProcessDumper.RULE_LANGUAGE + "\">" + XmlDumper.replaceIllegalChars(condition) + "</condition>" + EOL);
        xmlDump.append("      </conditionalEventDefinition>" + EOL);
        endNode("intermediateCatchEvent", xmlDump);
    }

}
