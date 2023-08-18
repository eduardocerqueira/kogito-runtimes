package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.FromDescr;
import org.drools.drl.ast.descr.PatternDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class FromHandler extends BaseAbstractHandler
        implements
        Handler {

    public FromHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);
        return new FromDescr();
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        final Element element = parser.endElementBuilder();

        final FromDescr fromDescr = (FromDescr) parser.getCurrent();

        Object parent = parser.getParent();

        final PatternDescr patternDescr = (PatternDescr) parent;

        if (element.getElementsByTagName("expression").getLength() > 0) {
            patternDescr.setSource(fromDescr);
        }

        return fromDescr;
    }

    public Class generateNodeFor() {
        return FromDescr.class;
    }

}
