package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ConnectiveDescr;
import org.drools.drl.ast.descr.ConnectiveDescr.RestrictionConnectiveType;
import org.drools.drl.ast.descr.ExprConstraintDescr;
import org.drools.drl.ast.descr.PatternDescr;
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
public class FieldConstraintHandler extends BaseAbstractHandler
        implements
        Handler {
    public FieldConstraintHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        final String fieldName = attrs.getValue("field-name");
        emptyAttributeCheck(localName,
                "field-name",
                fieldName,
                parser);
        final ConnectiveDescr connective = new ConnectiveDescr(RestrictionConnectiveType.AND);
        connective.setParen(false);

        connective.setPrefix(fieldName);

        return connective;
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();

        final ConnectiveDescr c = (ConnectiveDescr) parser.getCurrent();

        Object p = parser.getParent();
        if (p instanceof PatternDescr) {
            StringBuilder sb = new StringBuilder();
            c.buildExpression(sb);

            ExprConstraintDescr expr = new ExprConstraintDescr();
            expr.setExpression(sb.toString());

            final PatternDescr patternDescr = (PatternDescr) parser.getParent();
            patternDescr.addConstraint(expr);

        } else if (p instanceof ConnectiveDescr) {
            ((ConnectiveDescr) p).add(c);
        }

        return c;
    }

    public Class generateNodeFor() {
        return ExprConstraintHandler.class;
    }
}
