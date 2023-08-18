package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.CollectDescr;
import org.drools.drl.ast.descr.ConditionalElementDescr;
import org.drools.drl.ast.descr.FromDescr;
import org.drools.drl.ast.descr.PatternDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class CollectHandler extends BaseAbstractHandler
        implements
        Handler {

    public CollectHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);
        return new CollectDescr();
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();
        final CollectDescr collectDescr = (CollectDescr) parser.getCurrent();

        final Object parent = parser.getParent();

        if (parent.getClass().getName().equals(FromDescr.class.getName())) {
            final PatternDescr resultPattern = (PatternDescr) parser.getParent(1);
            resultPattern.setSource(collectDescr);
        } else if (parent instanceof ConditionalElementDescr) {
            final ConditionalElementDescr parentDescr = (ConditionalElementDescr) parent;
            parentDescr.addDescr(collectDescr);
        }

        return collectDescr;
    }

    public Class generateNodeFor() {
        return CollectDescr.class;
    }

}
