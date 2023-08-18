package org.jbpm.bpmn2.xml;

import java.util.HashSet;

import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DocumentationHandler extends BaseAbstractHandler implements Handler {

    @SuppressWarnings("unchecked")
    public DocumentationHandler() {
        if ((this.validParents == null) && (this.validPeers == null)) {
            this.validParents = new HashSet();
            this.validParents.add(Object.class);

            this.validPeers = new HashSet();
            this.validPeers.add(null);
            this.validPeers.add(Object.class);

            this.allowNesting = false;
        }
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName, attrs);
        return null;
    }

    public Object end(final String uri, final String localName,
            final Parser parser) throws SAXException {
        Element element = parser.endElementBuilder();
        Object parent = parser.getParent();
        if (parent instanceof NodeImpl) {

            ((NodeImpl) parent).getMetaData().put("Documentation", extractDocumentationText(element));
        } else if (parent instanceof org.jbpm.process.core.Process) {

            ((org.jbpm.process.core.Process) parent).getMetaData().put("Documentation", extractDocumentationText(element));
        }
        return parser.getCurrent();
    }

    public Class<?> generateNodeFor() {
        return null;
    }

    protected String extractDocumentationText(Element element) {
        String text = ((Text) element.getChildNodes().item(0)).getWholeText();
        if (text != null) {
            text = text.trim();
            if ("".equals(text)) {
                text = null;
            }
        }

        return text;
    }
}
