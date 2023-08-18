package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ExprConstraintDescr;
import org.drools.drl.ast.descr.PatternDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ExprConstraintHandler extends BaseAbstractHandler
        implements
        Handler {
    public ExprConstraintHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        return "";
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        final Element element = parser.endElementBuilder();
        final String expression = ((org.w3c.dom.Text) element.getChildNodes().item(0)).getWholeText();
        emptyContentCheck(localName, expression, parser);

        PatternDescr p = (PatternDescr) parser.getParent();

        ExprConstraintDescr descr = new ExprConstraintDescr(expression);

        p.getConstraint().addDescr(descr);

        return descr;
    }

    public Class generateNodeFor() {
        return ExprConstraintDescr.class;
    }
}
