package org.jbpm.bpmn2.xml;

import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.WorkItemNode;
import org.xml.sax.Attributes;

public class WorkItemNodeHandler extends AbstractNodeHandler {

    protected Node createNode(Attributes attrs) {
        throw new IllegalArgumentException("Reading in should be handled by specific handlers");
    }

    public Class<WorkItemNode> generateNodeFor() {
        return WorkItemNode.class;
    }

    public void writeNode(Node node, StringBuilder xmlDump, int metaDataType) {
        WorkItemNode workItemNode = (WorkItemNode) node;
        String type = workItemNode.getWork().getName();
        if ("Manual Task".equals(type)) {
            writeNode("manualTask", workItemNode, xmlDump, metaDataType);
            xmlDump.append(">" + EOL);
            writeExtensionElements(workItemNode, xmlDump);
            endNode("manualTask", xmlDump);
            return;
        }
        if ("Service Task".equals(type)) {
            writeNode("serviceTask", workItemNode, xmlDump, metaDataType);
            String impl = "Other";
            if (workItemNode.getWork().getParameter("implementation") != null) {
                impl = (String) workItemNode.getWork().getParameter("implementation");
            }
            xmlDump.append("operationRef=\"" +
                    XmlBPMNProcessDumper.getUniqueNodeId(workItemNode) + "_ServiceOperation\" implementation=\"" + impl + "\" >" + EOL);
            writeExtensionElements(workItemNode, xmlDump);
            writeIO(workItemNode.getIoSpecification(), xmlDump);
            endNode("serviceTask", xmlDump);
            return;
        }
        if ("Send Task".equals(type)) {
            writeNode("sendTask", workItemNode, xmlDump, metaDataType);
            xmlDump.append("messageRef=\"" +
                    XmlBPMNProcessDumper.getUniqueNodeId(workItemNode) + "_Message\" implementation=\"Other\" >" + EOL);
            writeExtensionElements(workItemNode, xmlDump);
            writeIO(workItemNode.getIoSpecification(), xmlDump);
            endNode("sendTask", xmlDump);
            return;
        }
        if ("Receive Task".equals(type)) {
            writeNode("receiveTask", workItemNode, xmlDump, metaDataType);
            String messageId = (String) workItemNode.getWork().getParameter("MessageId");
            xmlDump.append("messageRef=\"" +
                    messageId + "\" implementation=\"Other\" >" + EOL);
            writeExtensionElements(workItemNode, xmlDump);
            writeIO(workItemNode.getIoSpecification(), xmlDump);
            endNode("receiveTask", xmlDump);
            return;
        }
        writeNode("task", workItemNode, xmlDump, metaDataType);
        Object isForCompensationObject = workItemNode.getMetaData("isForCompensation");
        if (isForCompensationObject != null && ((Boolean) isForCompensationObject)) {
            xmlDump.append("isForCompensation=\"true\" ");
        }
        xmlDump.append("tns:taskName=\"" + XmlBPMNProcessDumper.replaceIllegalCharsAttribute(type) + "\" >" + EOL);
        writeExtensionElements(workItemNode, xmlDump);
        writeIO(workItemNode.getIoSpecification(), xmlDump);
        endNode("task", xmlDump);
    }

}
