package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.BaseDescr;
import org.drools.drl.ast.descr.FromDescr;
import org.drools.drl.ast.descr.MVELExprDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ExpressionHandler extends BaseAbstractHandler
        implements
        Handler {

    public ExpressionHandler() {
    }

    public Class generateNodeFor() {
        return BaseDescr.class;
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        return new BaseDescr();
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        final Element element = parser.endElementBuilder();

        final String expression = ((org.w3c.dom.Text) element.getChildNodes().item(0)).getWholeText();

        emptyContentCheck(localName, expression, parser);

        FromDescr parent = (FromDescr) parser.getParent();
        parent.setDataSource(new MVELExprDescr(expression.trim()));
        return null;
    }
}
