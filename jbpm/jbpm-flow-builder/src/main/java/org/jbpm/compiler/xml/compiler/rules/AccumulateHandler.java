package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.AccumulateDescr;
import org.drools.drl.ast.descr.ConditionalElementDescr;
import org.drools.drl.ast.descr.FromDescr;
import org.drools.drl.ast.descr.PatternDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class AccumulateHandler extends BaseAbstractHandler
        implements
        Handler {

    public AccumulateHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {

        parser.startElementBuilder(localName, attrs);
        return new AccumulateDescr();
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {

        parser.endElementBuilder();
        final AccumulateDescr accumulateDescr = (AccumulateDescr) parser.getCurrent();

        final Object parent = parser.getParent();

        if (parent.getClass().getName().equals(FromDescr.class.getName())) {
            final PatternDescr result = (PatternDescr) parser.getParent(1);
            result.setSource(accumulateDescr);

        } else if (parent instanceof ConditionalElementDescr) {
            final ConditionalElementDescr parentDescr = (ConditionalElementDescr) parent;
            parentDescr.addDescr(accumulateDescr);
        }

        return accumulateDescr;
    }

    public Class generateNodeFor() {
        return AccumulateDescr.class;
    }

}
