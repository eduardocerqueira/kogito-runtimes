package org.jbpm.bpmn2.xml;

import java.util.Map;

import org.jbpm.workflow.core.Constraint;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.impl.ConnectionRef;
import org.jbpm.workflow.core.node.Split;
import org.xml.sax.Attributes;

public class SplitHandler extends AbstractNodeHandler {

    protected Node createNode(Attributes attrs) {
        throw new IllegalArgumentException("Reading in should be handled by gateway handler");
    }

    @SuppressWarnings("unchecked")
    public Class generateNodeFor() {
        return Split.class;
    }

    public void writeNode(Node node, StringBuilder xmlDump, int metaDataType) {
        Split split = (Split) node;
        String type = null;
        switch (split.getType()) {
            case Split.TYPE_AND:
                type = "parallelGateway";
                writeNode(type, node, xmlDump, metaDataType);
                break;
            case Split.TYPE_XOR:
                type = "exclusiveGateway";
                writeNode(type, node, xmlDump, metaDataType);
                for (Map.Entry<ConnectionRef, Constraint> entry : split.getConstraints().entrySet()) {
                    if (entry.getValue() != null && entry.getValue().isDefault()) {
                        xmlDump.append("default=\"" +
                                XmlBPMNProcessDumper.getUniqueNodeId(split) + "-" +
                                XmlBPMNProcessDumper.getUniqueNodeId(node.getParentContainer().getNode(entry.getKey().getNodeId())) +
                                "\" ");
                        break;
                    }
                }
                break;
            case Split.TYPE_OR:
                type = "inclusiveGateway";
                writeNode(type, node, xmlDump, metaDataType);
                for (Map.Entry<ConnectionRef, Constraint> entry : split.getConstraints().entrySet()) {
                    if (entry.getValue() != null && entry.getValue().isDefault()) {
                        xmlDump.append("default=\"" +
                                XmlBPMNProcessDumper.getUniqueNodeId(split) + "-" +
                                XmlBPMNProcessDumper.getUniqueNodeId(node.getParentContainer().getNode(entry.getKey().getNodeId())) +
                                "\" ");
                        break;
                    }
                }
                break;
            case Split.TYPE_XAND:
                type = "eventBasedGateway";
                writeNode(type, node, xmlDump, metaDataType);
                break;
            default:
                type = "complexGateway";
                writeNode(type, node, xmlDump, metaDataType);
        }
        xmlDump.append("gatewayDirection=\"Diverging\" >" + EOL);
        writeExtensionElements(node, xmlDump);
        endNode(type, xmlDump);
    }

}
