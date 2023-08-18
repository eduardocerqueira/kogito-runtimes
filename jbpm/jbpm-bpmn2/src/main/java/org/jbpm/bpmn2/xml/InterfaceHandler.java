package org.jbpm.bpmn2.xml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jbpm.bpmn2.core.*;
import org.jbpm.bpmn2.core.Error;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.ProcessBuildData;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class InterfaceHandler extends BaseAbstractHandler implements Handler {

    @SuppressWarnings("unchecked")
    public InterfaceHandler() {
        if ((this.validParents == null) && (this.validPeers == null)) {
            this.validParents = new HashSet();
            this.validParents.add(Definitions.class);

            this.validPeers = new HashSet();
            this.validPeers.add(null);
            this.validPeers.add(ItemDefinition.class);
            this.validPeers.add(Message.class);
            this.validPeers.add(Interface.class);
            this.validPeers.add(Escalation.class);
            this.validPeers.add(Error.class);
            this.validPeers.add(Signal.class);
            this.validPeers.add(DataStore.class);
            this.validPeers.add(RuleFlowProcess.class);

            this.allowNesting = false;
        }
    }

    @SuppressWarnings("unchecked")
    public Object start(final String uri, final String localName,
            final Attributes attrs, final Parser parser)
            throws SAXException {
        parser.startElementBuilder(localName, attrs);

        String id = attrs.getValue("id");
        String name = attrs.getValue("name");
        String implRef = attrs.getValue("implementationRef");

        if (name == null || name.isEmpty()) {
            throw new MalformedNodeException(id, name, "interface name is a required attribute");
        }

        ProcessBuildData buildData = (ProcessBuildData) parser.getData();
        List<Interface> interfaces = (List<Interface>) buildData.getMetaData("Interfaces");
        if (interfaces == null) {
            interfaces = new ArrayList<>();
            buildData.setMetaData("Interfaces", interfaces);
        }
        Interface i = new Interface(id, name);
        if (implRef != null) {
            i.setImplementationRef(implRef);
        }
        interfaces.add(i);
        return i;
    }

    public Object end(final String uri, final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();
        return parser.getCurrent();
    }

    public Class<?> generateNodeFor() {
        return Interface.class;
    }

}
