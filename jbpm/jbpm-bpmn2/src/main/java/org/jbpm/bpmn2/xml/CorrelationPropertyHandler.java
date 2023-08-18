package org.jbpm.bpmn2.xml;

import org.jbpm.bpmn2.core.CorrelationProperty;
import org.jbpm.bpmn2.core.Expression;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class CorrelationPropertyHandler extends BaseAbstractHandler implements Handler {

    @Override
    public Object start(String uri, String localName, Attributes attrs, Parser parser) throws SAXException {
        parser.startElementBuilder(localName, attrs);

        String correlationPropertyId = attrs.getValue("id");
        String correlationPropertyName = attrs.getValue("name");
        String type = attrs.getValue("type");

        CorrelationProperty correlationProperty = new CorrelationProperty();
        correlationProperty.setId(correlationPropertyId);
        correlationProperty.setName(correlationPropertyName);
        correlationProperty.setType(HandlerUtil.definitions(parser).get(type).getStructureRef());

        HandlerUtil.correlationProperties(parser).put(correlationPropertyId, correlationProperty);
        return correlationProperty;
    }

    @Override
    public Object end(String uri, String localName, Parser parser) throws SAXException {
        Element element = parser.endElementBuilder();
        CorrelationProperty correlationProperty = (CorrelationProperty) parser.getCurrent();

        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if ("correlationPropertyRetrievalExpression".equals(node.getNodeName())) {
                String messageRef = ((Element) node).getAttribute("messageRef");
                correlationProperty.setRetrievalExpression(messageRef, buildMessagePathExpression(node.getChildNodes(), parser));
            }
        }
        return null;
    }

    private Expression buildMessagePathExpression(NodeList childNodes, Parser parser) {
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if ("messagePath".equals(node.getNodeName())) {
                Element expressionElement = (Element) node;
                Expression expression = new Expression();
                expression.setId(expressionElement.getAttribute("id"));
                expression.setLang(expressionElement.getAttribute("language"));
                expression.setScript(expressionElement.getTextContent());
                expression.setOutcomeType(HandlerUtil.definitions(parser).get(expressionElement.getAttribute("evaluatesToTypeRef")).getStructureRef());
                return expression;
            }
        }
        throw new RuntimeException("message Path not found for correlation property " + parser.getCurrent());
    }

    @Override
    public Class<?> generateNodeFor() {
        return CorrelationProperty.class;
    }

}
