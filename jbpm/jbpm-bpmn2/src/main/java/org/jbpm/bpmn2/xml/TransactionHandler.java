package org.jbpm.bpmn2.xml;

import org.jbpm.compiler.xml.Parser;
import org.jbpm.workflow.core.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class TransactionHandler extends SubProcessHandler {

    @Override
    protected Node handleNode(final Node node, final Element element, final String uri,
            final String localName, final Parser parser) throws SAXException {
        super.handleNode(node, element, uri, localName, parser);
        node.setMetaData("Transaction", true);
        return node;
    }

    public void writeNode(Node node, StringBuilder xmlDump, boolean includeMeta) {
        throw new IllegalArgumentException("Writing out should be handled by specific handlers");
    }

}
