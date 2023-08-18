package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.AndDescr;
import org.drools.drl.ast.descr.ConditionalElementDescr;
import org.drools.drl.ast.descr.MultiPatternDestinationDescr;
import org.drools.drl.ast.descr.QueryDescr;
import org.drools.drl.ast.descr.RuleDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AndHandler extends BaseAbstractHandler
        implements
        Handler {
    public AndHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);
        return new AndDescr();
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();

        final AndDescr andDescr = (AndDescr) parser.getCurrent();

        final Object parent = parser.getParent();

        if (!andDescr.getDescrs().isEmpty()) {
            if (parent instanceof RuleDescr || parent instanceof QueryDescr) {
                final RuleDescr ruleDescr = (RuleDescr) parent;
                ruleDescr.setLhs(andDescr);
            } else if (parent instanceof MultiPatternDestinationDescr) {
                final MultiPatternDestinationDescr mpDescr = (MultiPatternDestinationDescr) parent;
                mpDescr.setInput(andDescr);
            } else if (parent instanceof ConditionalElementDescr) {
                final ConditionalElementDescr ceDescr = (ConditionalElementDescr) parent;
                ceDescr.addDescr(andDescr);
            }
        }

        return andDescr;
    }

    public Class generateNodeFor() {
        return AndDescr.class;
    }
}
