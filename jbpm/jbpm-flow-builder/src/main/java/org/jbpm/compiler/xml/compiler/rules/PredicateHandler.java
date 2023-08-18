package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ExprConstraintDescr;
import org.drools.drl.ast.descr.PatternDescr;
import org.drools.drl.ast.descr.PredicateDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PredicateHandler extends BaseAbstractHandler
        implements
        Handler {
    public PredicateHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);
        return ""; // need to return something, otherwise it'll pop the parent
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        final Element element = parser.endElementBuilder();

        final String expression = ((org.w3c.dom.Text) element.getChildNodes().item(0)).getWholeText();

        if (expression == null || expression.trim().equals("")) {
            throw new SAXParseException("<predicate> must have some content",
                    parser.getLocator());
        }

        final PatternDescr patternDescr = (PatternDescr) parser.getParent();

        ExprConstraintDescr expr = new ExprConstraintDescr("eval(" + expression + ")");

        patternDescr.addConstraint(expr);

        return expr;
    }

    public Class generateNodeFor() {
        return PredicateDescr.class;
    }
}
