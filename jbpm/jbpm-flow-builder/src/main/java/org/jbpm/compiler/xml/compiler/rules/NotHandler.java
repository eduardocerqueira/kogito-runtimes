package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ConditionalElementDescr;
import org.drools.drl.ast.descr.NotDescr;
import org.drools.drl.ast.descr.PatternDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NotHandler extends BaseAbstractHandler
        implements
        Handler {
    public NotHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);
        return new NotDescr();
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();

        final NotDescr notDescr = (NotDescr) parser.getCurrent();

        if ((notDescr.getDescrs().size() != 1) && (notDescr.getDescrs().get(0).getClass() != PatternDescr.class)) {
            throw new SAXParseException("<not> can only have a single <pattern...> as a child element",
                    parser.getLocator());
        }

        final ConditionalElementDescr parentDescr = (ConditionalElementDescr) parser.getParent();
        parentDescr.addDescr(notDescr);

        return null;
    }

    public Class generateNodeFor() {
        return NotDescr.class;
    }
}
