package org.jbpm.bpmn2.xml;

import org.jbpm.compiler.xml.Handler;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.CatchLinkNode;
import org.xml.sax.Attributes;

import static org.jbpm.ruleflow.core.Metadata.LINK_NAME;

public class CatchLinkNodeHandler extends AbstractNodeHandler implements
        Handler {

    public Class<?> generateNodeFor() {
        return CatchLinkNode.class;
    }

    @Override
    protected Node createNode(Attributes attrs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeNode(Node node, StringBuilder xmlDump, int metaDataType) {

        CatchLinkNode linkNode = (CatchLinkNode) node;
        writeNode("intermediateCatchEvent", linkNode, xmlDump, metaDataType);
        xmlDump.append(">" + EOL);
        writeExtensionElements(linkNode, xmlDump);

        String name = (String) node.getMetaData().get(LINK_NAME);

        xmlDump.append("<linkEventDefinition name=\"" + name + "\" >" + EOL);

        Object target = linkNode.getMetaData("target");
        if (null != target) {
            xmlDump.append(String.format("<target>%s</target>", target) + EOL);
        }
        xmlDump.append("</linkEventDefinition>" + EOL);
        endNode("intermediateCatchEvent", xmlDump);

    }

}
