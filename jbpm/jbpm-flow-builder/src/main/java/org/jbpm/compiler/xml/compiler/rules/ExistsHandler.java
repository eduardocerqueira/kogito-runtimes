package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ConditionalElementDescr;
import org.drools.drl.ast.descr.ExistsDescr;
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
public class ExistsHandler extends BaseAbstractHandler
        implements
        Handler {
    public ExistsHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);
        return new ExistsDescr();
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();

        final ExistsDescr existsDescr = (ExistsDescr) parser.getCurrent();

        if ((existsDescr.getDescrs().size() != 1) && (existsDescr.getDescrs().get(0).getClass() != PatternDescr.class)) {
            throw new SAXParseException("<exists> can only have a single <pattern...> as a child element",
                    parser.getLocator());
        }

        final ConditionalElementDescr parentDescr = (ConditionalElementDescr) parser.getParent();
        parentDescr.addDescr(existsDescr);

        return existsDescr;
    }

    public Class generateNodeFor() {
        return ExistsDescr.class;
    }
}
