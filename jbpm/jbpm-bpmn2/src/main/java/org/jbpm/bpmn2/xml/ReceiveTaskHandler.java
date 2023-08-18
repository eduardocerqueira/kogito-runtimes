package org.jbpm.bpmn2.xml;

import java.util.Map;

import org.jbpm.bpmn2.core.Message;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.ProcessBuildData;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.WorkItemNode;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ReceiveTaskHandler extends TaskHandler {

    @Override
    protected Node createNode(Attributes attrs) {
        return new WorkItemNode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class generateNodeFor() {
        return Node.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Node handleNode(final Node node, final Element element, final String uri,
            final String localName, final Parser parser) throws SAXException {
        super.handleNode(node, element, uri, localName, parser);
        WorkItemNode workItemNode = (WorkItemNode) node;
        String messageRef = element.getAttribute("messageRef");
        Map<String, Message> messages = (Map<String, Message>) ((ProcessBuildData) parser.getData()).getMetaData("Messages");
        if (messages == null) {
            throw new ProcessParsingValidationException("No messages found");
        }
        Message message = messages.get(messageRef);
        if (message == null) {
            throw new ProcessParsingValidationException("Could not find message " + messageRef);
        }
        workItemNode.getWork().setParameter("MessageId", message.getId());
        workItemNode.getWork().setParameter("MessageType", message.getType());
        return node;
    }

    @Override
    protected String getTaskName(final Element element) {
        return "Receive Task";
    }

    public void writeNode(Node node, StringBuilder xmlDump, boolean includeMeta) {
        throw new IllegalArgumentException("Writing out should be handled by WorkItemNodeHandler");
    }
}
