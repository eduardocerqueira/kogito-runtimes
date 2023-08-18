package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.BindingDescr;
import org.drools.drl.ast.descr.PatternDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class FieldBindingHandler extends BaseAbstractHandler
        implements
        Handler {
    public FieldBindingHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        final String identifier = attrs.getValue("identifier");
        final String fieldName = attrs.getValue("field-name");

        emptyAttributeCheck(localName,
                "identifier",
                identifier,
                parser);
        emptyAttributeCheck(localName,
                "fieldName",
                fieldName,
                parser);

        return new BindingDescr(identifier,
                fieldName);
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();

        final BindingDescr fieldBindingDescr = (BindingDescr) parser.getCurrent();

        final PatternDescr patternDescr = (PatternDescr) parser.getParent();

        patternDescr.addConstraint(fieldBindingDescr);

        return fieldBindingDescr;
    }

    public Class generateNodeFor() {
        return BindingDescr.class;
    }
}
