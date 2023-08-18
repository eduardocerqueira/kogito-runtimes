package org.jbpm.bpmn2.xml;

import java.util.HashMap;

import org.jbpm.compiler.xml.Parser;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.SubProcessNode;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class CallActivityHandler extends AbstractNodeHandler {

    protected Node createNode(Attributes attrs) {
        return new SubProcessNode();
    }

    public Class<SubProcessNode> generateNodeFor() {
        return SubProcessNode.class;
    }

    @Override
    protected Node handleNode(final Node node, final Element element, final String uri,
            final String localName, final Parser parser) throws SAXException {
        super.handleNode(node, element, uri, localName, parser);
        SubProcessNode subProcessNode = (SubProcessNode) node;
        String processId = element.getAttribute("calledElement");
        if (processId != null && processId.length() > 0) {
            subProcessNode.setProcessId(processId);
        } else {
            String processName = element.getAttribute("calledElementByName");
            subProcessNode.setProcessName(processName);
        }
        String waitForCompletion = element.getAttribute("waitForCompletion");
        if (waitForCompletion != null && "false".equals(waitForCompletion)) {
            subProcessNode.setWaitForCompletion(false);
        }
        String independent = element.getAttribute("independent");
        if (independent != null && "false".equals(independent)) {
            subProcessNode.setIndependent(false);
        }

        subProcessNode.setMetaData("DataInputs", new HashMap<String, String>());
        subProcessNode.setMetaData("DataOutputs", new HashMap<String, String>());

        handleScript(subProcessNode, element, "onEntry");
        handleScript(subProcessNode, element, "onExit");

        Node currentNode = subProcessNode;
        subProcessNode.setIoSpecification(readIOEspecification(parser, element));
        subProcessNode.setMultiInstanceSpecification(readMultiInstanceSpecification(parser, element, subProcessNode.getIoSpecification()));
        if (subProcessNode.getMultiInstanceSpecification().hasMultiInstanceInput()) {
            currentNode = decorateMultiInstanceSpecificationActivity(subProcessNode, subProcessNode.getMultiInstanceSpecification());
        }
        return currentNode;

    }

    public void writeNode(Node node, StringBuilder xmlDump, int metaDataType) {
        SubProcessNode subProcessNode = (SubProcessNode) node;
        writeNode("callActivity", subProcessNode, xmlDump, metaDataType);
        if (subProcessNode.getProcessId() != null) {
            xmlDump.append("calledElement=\"" + XmlBPMNProcessDumper.replaceIllegalCharsAttribute(subProcessNode.getProcessId()) + "\" ");
        }
        if (!subProcessNode.isWaitForCompletion()) {
            xmlDump.append("tns:waitForCompletion=\"false\" ");
        }
        if (!subProcessNode.isIndependent()) {
            xmlDump.append("tns:independent=\"false\" ");
        }
        xmlDump.append(">" + EOL);
        writeExtensionElements(subProcessNode, xmlDump);
        writeIO(subProcessNode.getIoSpecification(), xmlDump);
        writeMultiInstance(subProcessNode.getMultiInstanceSpecification(), xmlDump);
        endNode("callActivity", xmlDump);
    }

}
