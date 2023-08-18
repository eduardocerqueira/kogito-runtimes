package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ConditionalElementDescr;
import org.drools.drl.ast.descr.PatternDescr;
import org.drools.drl.ast.descr.PatternDestinationDescr;
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
public class PatternHandler extends BaseAbstractHandler
        implements
        Handler {
    public PatternHandler() {

    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        final String objectType = attrs.getValue("object-type");

        if (objectType == null || objectType.trim().equals("")) {
            throw new SAXParseException("<pattern> requires an 'object-type' attribute",
                    parser.getLocator());
        }

        PatternDescr patternDescr = null;

        final String identifier = attrs.getValue("identifier");
        if (identifier == null || identifier.trim().equals("")) {
            patternDescr = new PatternDescr(objectType);
        } else {
            patternDescr = new PatternDescr(objectType,
                    identifier);
        }

        return patternDescr;
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();
        final PatternDescr patternDescr = (PatternDescr) parser.getCurrent();

        final Object parent = parser.getParent();

        if (parent instanceof PatternDestinationDescr) {
            final PatternDestinationDescr parentDescr = (PatternDestinationDescr) parent;
            parentDescr.setInputPattern(patternDescr);
        } else {
            final ConditionalElementDescr parentDescr = (ConditionalElementDescr) parent;
            parentDescr.addDescr(patternDescr);
        }
        return patternDescr;
    }

    public Class generateNodeFor() {
        return PatternDescr.class;
    }
}
