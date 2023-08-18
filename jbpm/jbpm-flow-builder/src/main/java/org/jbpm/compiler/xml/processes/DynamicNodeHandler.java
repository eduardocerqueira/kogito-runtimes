package org.jbpm.compiler.xml.processes;

import org.jbpm.compiler.xml.Parser;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.DynamicNode;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import static org.jbpm.ruleflow.core.Metadata.COMPLETION_CONDITION;

public class DynamicNodeHandler extends CompositeNodeHandler {

    public static final String AUTOCOMPLETE_COMPLETION_CONDITION = "autocomplete";

    @Override
    protected Node createNode() {
        return new DynamicNode();
    }

    @Override
    public Class<?> generateNodeFor() {
        return DynamicNode.class;
    }

    @Override
    protected String getNodeName() {
        return "dynamic";
    }

    @Override
    protected void handleNode(Node node, Element element, String uri, String localName, Parser parser) throws SAXException {
        super.handleNode(node, element, uri, localName, parser);
        DynamicNode dynamicNode = (DynamicNode) node;
        for (int i = 0; i < element.getChildNodes().getLength(); i++) {
            org.w3c.dom.Node n = element.getChildNodes().item(i);
            if (COMPLETION_CONDITION.equals(n.getNodeName())) {
                if (AUTOCOMPLETE_COMPLETION_CONDITION.equals(n.getTextContent())) {
                    dynamicNode.setAutoComplete(true);
                } else {
                    dynamicNode.setCompletionCondition(n.getTextContent());
                }
            }
        }
    }
}
