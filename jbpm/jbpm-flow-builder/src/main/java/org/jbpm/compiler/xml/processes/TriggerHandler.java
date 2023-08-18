package org.jbpm.compiler.xml.processes;

import java.util.HashSet;

import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.jbpm.workflow.core.node.ConstraintTrigger;
import org.jbpm.workflow.core.node.EventTrigger;
import org.jbpm.workflow.core.node.StartNode;
import org.jbpm.workflow.core.node.Trigger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class TriggerHandler extends BaseAbstractHandler implements Handler {

    public TriggerHandler() {
        if ((this.validParents == null) && (this.validPeers == null)) {
            this.validParents = new HashSet<Class<?>>();
            this.validParents.add(StartNode.class);

            this.validPeers = new HashSet<Class<?>>();
            this.validPeers.add(null);
            this.validPeers.add(Trigger.class);

            this.allowNesting = false;
        }
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName, attrs);
        StartNode startNode = (StartNode) parser.getParent();
        String type = attrs.getValue("type");
        emptyAttributeCheck(localName, "type", type, parser);

        Trigger trigger = null;
        if ("constraint".equals(type)) {
            trigger = new ConstraintTrigger();
        } else if ("event".equals(type)) {
            trigger = new EventTrigger();
        } else {
            throw new SAXException("Unknown trigger type " + type);
        }
        startNode.addTrigger(trigger);
        return trigger;
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();
        return parser.getCurrent();
    }

    @SuppressWarnings("unchecked")
    public Class generateNodeFor() {
        return Trigger.class;
    }

}
