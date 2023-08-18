package org.jbpm.compiler.xml.compiler.rules;

import java.math.BigDecimal;

import org.drools.drl.ast.descr.ConnectiveDescr;
import org.drools.drl.ast.descr.LiteralRestrictionDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class LiteralRestrictionHandler extends BaseAbstractHandler
        implements
        Handler {
    public LiteralRestrictionHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        String evaluator = attrs.getValue("evaluator");
        emptyAttributeCheck(localName, "evaluator", evaluator, parser);

        String text = attrs.getValue("value");

        if (!text.trim().equals("null")) {
            // find out if it's a valid integer or decimal, if not wrap in quotes
            try {
                new BigDecimal(text);
            } catch (NumberFormatException e) {
                text = "\"" + text.trim() + "\"";
            }
        }

        return evaluator.trim() + " " + text.trim();
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();

        ConnectiveDescr c = (ConnectiveDescr) parser.getParent();
        String s = (String) parser.getCurrent();

        c.add(s);
        return null;
    }

    public Class generateNodeFor() {
        return LiteralRestrictionDescr.class;
    }
}
