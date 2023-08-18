package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ConnectiveDescr;
import org.drools.drl.ast.descr.ConnectiveDescr.RestrictionConnectiveType;
import org.drools.drl.ast.descr.ExprConstraintDescr;
import org.drools.drl.ast.descr.PatternDescr;
import org.drools.drl.ast.descr.RestrictionConnectiveDescr;
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
public class RestrictionConnectiveHandler extends BaseAbstractHandler
        implements
        Handler {

    public static final String AND = "and-";
    public static final String OR = "or-";

    public RestrictionConnectiveHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        if (localName.startsWith(RestrictionConnectiveHandler.OR)) {
            return new ConnectiveDescr(RestrictionConnectiveType.OR);
        } else if (localName.startsWith(RestrictionConnectiveHandler.AND)) {
            return new ConnectiveDescr(RestrictionConnectiveType.AND);
        } else {
            throw new SAXParseException("<" + localName + "> should have'",
                    parser.getLocator());
        }

    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();
        Object op = parser.getParent();
        ConnectiveDescr c = (ConnectiveDescr) parser.getCurrent();

        if (op instanceof PatternDescr) {
            StringBuilder sb = new StringBuilder();
            c.buildExpression(sb);

            ExprConstraintDescr expr = new ExprConstraintDescr();
            expr.setExpression(sb.toString());

            final PatternDescr patternDescr = (PatternDescr) op;
            patternDescr.addConstraint(expr);
        } else {
            ConnectiveDescr p = (ConnectiveDescr) op;
            p.add(c);
        }
        return c;
    }

    public Class generateNodeFor() {
        return RestrictionConnectiveDescr.class;
    }
}
