package org.jbpm.bpmn2.xml;

import java.util.HashMap;
import java.util.Map;

import org.drools.mvel.java.JavaDialect;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.impl.DroolsConsequenceAction;
import org.jbpm.workflow.core.node.ActionNode;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ScriptTaskHandler extends AbstractNodeHandler {

    private static Map<String, String> SUPPORTED_SCRIPT_FORMATS = new HashMap<>();

    static {
        SUPPORTED_SCRIPT_FORMATS.put(XmlBPMNProcessDumper.JAVA_LANGUAGE, JavaDialect.ID);
        SUPPORTED_SCRIPT_FORMATS.put(XmlBPMNProcessDumper.FEEL_LANGUAGE, "FEEL");
        SUPPORTED_SCRIPT_FORMATS.put(XmlBPMNProcessDumper.FEEL_LANGUAGE_SHORT, "FEEL");
    }

    public static void registerSupportedScriptFormat(String language, String dialect) {
        SUPPORTED_SCRIPT_FORMATS.put(language, dialect);
    }

    protected Node createNode(Attributes attrs) {
        ActionNode result = new ActionNode();
        result.setAction(new DroolsConsequenceAction());
        return result;
    }

    public Class<Node> generateNodeFor() {
        return Node.class;
    }

    @Override
    protected Node handleNode(final Node node, final Element element, final String uri,
            final String localName, final Parser parser) throws SAXException {
        super.handleNode(node, element, uri, localName, parser);
        ActionNode actionNode = (ActionNode) node;
        node.setMetaData("NodeType", "ScriptTask");
        DroolsConsequenceAction action = (DroolsConsequenceAction) actionNode.getAction();
        if (action == null) {
            action = new DroolsConsequenceAction();
            actionNode.setAction(action);
        }
        String language = element.getAttribute("scriptFormat");
        action.setDialect(SUPPORTED_SCRIPT_FORMATS.getOrDefault(language, "java"));
        action.setConsequence("");
        final DroolsConsequenceAction scriptAction = action;
        readSingleChildElementByTag(element, "script").ifPresent(script -> {
            scriptAction.setConsequence(script.getTextContent());
        });

        actionNode.setMetaData("DataInputs", new HashMap<String, String>());
        actionNode.setMetaData("DataOutputs", new HashMap<String, String>());

        String compensation = element.getAttribute("isForCompensation");
        if (compensation != null) {
            boolean isForCompensation = Boolean.parseBoolean(compensation);
            if (isForCompensation) {
                actionNode.setMetaData("isForCompensation", isForCompensation);
            }
        }

        Node currentNode = actionNode;
        actionNode.setIoSpecification(readIOEspecification(parser, element));
        actionNode.setMultiInstanceSpecification(readMultiInstanceSpecification(parser, element, actionNode.getIoSpecification()));
        return currentNode;
    }

    public void writeNode(Node node, StringBuilder xmlDump, int metaDataType) {
        throw new IllegalArgumentException("Writing out should be handled by action node handler");
    }

}
