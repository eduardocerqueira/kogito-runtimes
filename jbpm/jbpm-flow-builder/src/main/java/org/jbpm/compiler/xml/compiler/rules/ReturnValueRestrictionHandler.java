package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ConnectiveDescr;
import org.drools.drl.ast.descr.ReturnValueRestrictionDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ReturnValueRestrictionHandler extends BaseAbstractHandler
        implements
        Handler {
    public ReturnValueRestrictionHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        String evaluator = attrs.getValue("evaluator");
        emptyAttributeCheck(localName, "evaluator", evaluator, parser);

        return evaluator.trim() + " ";
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        final Element element = parser.endElementBuilder();
        final String expression = ((org.w3c.dom.Text) element.getChildNodes().item(0)).getWholeText();
        emptyContentCheck(localName, expression, parser);

        ConnectiveDescr c = (ConnectiveDescr) parser.getParent();
        String s = parser.getCurrent() + "(" + expression + ")";

        c.add(s);
        return null;
    }

    public Class generateNodeFor() {
        return ReturnValueRestrictionDescr.class;
    }
}
