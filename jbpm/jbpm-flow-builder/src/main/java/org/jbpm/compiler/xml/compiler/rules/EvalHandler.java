package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ConditionalElementDescr;
import org.drools.drl.ast.descr.EvalDescr;
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
public class EvalHandler extends BaseAbstractHandler
        implements
        Handler {
    public EvalHandler() {
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        return new EvalDescr();
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        final Element element = parser.endElementBuilder();

        final EvalDescr evalDescr = (EvalDescr) parser.getCurrent();

        final String expression = ((org.w3c.dom.Text) element.getChildNodes().item(0)).getWholeText();

        emptyContentCheck(localName, expression, parser);

        evalDescr.setContent(expression);

        final ConditionalElementDescr parentDescr = (ConditionalElementDescr) parser.getParent();
        parentDescr.addDescr(evalDescr);

        return evalDescr;
    }

    public Class generateNodeFor() {
        return EvalDescr.class;
    }
}
