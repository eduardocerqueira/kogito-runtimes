package org.jbpm.compiler.xml.processes;

import org.jbpm.compiler.xml.Parser;
import org.jbpm.process.core.Work;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.HumanTaskNode;
import org.jbpm.workflow.core.node.WorkItemNode;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class HumanTaskNodeHandler extends WorkItemNodeHandler {

    @Override
    public void handleNode(final Node node, final Element element, final String uri,
            final String localName, final Parser parser)
            throws SAXException {
        super.handleNode(node, element, uri, localName, parser);
        HumanTaskNode humanTaskNode = (HumanTaskNode) node;
        final String swimlane = element.getAttribute("swimlane");
        if (swimlane != null && !"".equals(swimlane)) {
            humanTaskNode.setSwimlane(swimlane);
        }
    }

    @Override
    protected Node createNode() {
        return new HumanTaskNode();
    }

    @Override
    public Class<?> generateNodeFor() {
        return HumanTaskNode.class;
    }

    @Override
    public void writeNode(Node node, StringBuilder xmlDump, boolean includeMeta) {
        WorkItemNode workItemNode = (WorkItemNode) node;
        writeNode("humanTask", workItemNode, xmlDump, includeMeta);
        visitParameters(workItemNode, xmlDump);
        xmlDump.append(">" + EOL);
        if (includeMeta) {
            writeMetaData(workItemNode, xmlDump);
        }
        Work work = workItemNode.getWork();
        visitWork(work, xmlDump, includeMeta);
        visitInMappings(workItemNode.getInMappings(), xmlDump);
        visitOutMappings(workItemNode.getOutMappings(), xmlDump);
        for (String eventType : workItemNode.getActionTypes()) {
            writeActions(eventType, workItemNode.getActions(eventType), xmlDump);
        }
        writeTimers(workItemNode.getTimers(), xmlDump);
        endNode("humanTask", xmlDump);
    }

    @Override
    protected void visitParameters(WorkItemNode workItemNode, StringBuilder xmlDump) {
        super.visitParameters(workItemNode, xmlDump);
        HumanTaskNode humanTaskNode = (HumanTaskNode) workItemNode;
        String swimlane = humanTaskNode.getSwimlane();
        if (swimlane != null) {
            xmlDump.append("swimlane=\"" + swimlane + "\" ");
        }
    }

}
