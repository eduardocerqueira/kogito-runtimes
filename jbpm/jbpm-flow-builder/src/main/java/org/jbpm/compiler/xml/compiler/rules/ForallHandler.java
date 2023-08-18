package org.jbpm.compiler.xml.compiler.rules;

import org.drools.drl.ast.descr.ConditionalElementDescr;
import org.drools.drl.ast.descr.ForallDescr;
import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ForallHandler extends BaseAbstractHandler
        implements
        Handler {

    public ForallHandler() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kie.xml.Handler#start(java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        return new ForallDescr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kie.xml.Handler#end(java.lang.String, java.lang.String)
     */
    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();

        final ForallDescr forallDescr = (ForallDescr) parser.getCurrent();

        final Object parent = parser.getParent();

        final ConditionalElementDescr parentDescr = (ConditionalElementDescr) parent;
        parentDescr.addDescr(forallDescr);

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.kie.xml.Handler#generateNodeFor()
     */
    public Class generateNodeFor() {
        return ForallDescr.class;
    }

}
