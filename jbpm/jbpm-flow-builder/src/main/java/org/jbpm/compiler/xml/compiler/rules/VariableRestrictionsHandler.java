package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ConnectiveDescr;
import org.drools.drl.ast.descr.VariableRestrictionDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class VariableRestrictionsHandler extends BaseAbstractHandler
        implements
        Handler {
    public VariableRestrictionsHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        String evaluator = attrs.getValue("evaluator");
        String identifier = attrs.getValue("identifier");

        emptyAttributeCheck(localName, "evaluator", evaluator, parser);
        emptyAttributeCheck(localName, identifier, "identifier", parser);

        return evaluator.trim() + " " + identifier.trim();
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
        return VariableRestrictionDescr.class;
    }
}
