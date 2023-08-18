package org.jbpm.compiler.xml.processes;

import java.util.HashSet;

import org.jbpm.compiler.xml.Handler;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.ProcessBuildData;
import org.jbpm.compiler.xml.core.BaseAbstractHandler;
import org.jbpm.ruleflow.core.RuleFlowProcess;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ProcessHandler extends BaseAbstractHandler implements Handler {

    public ProcessHandler() {
        if ((this.validParents == null) && (this.validPeers == null)) {
            this.validParents = new HashSet();
            this.validParents.add(null);

            this.validPeers = new HashSet();
            this.validPeers.add(null);

            this.allowNesting = false;
        }
    }

    public Object start(final String uri,
            final String localName,
            final Attributes attrs,
            final Parser parser) throws SAXException {
        parser.startElementBuilder(localName,
                attrs);

        final String id = attrs.getValue("id");
        final String name = attrs.getValue("name");
        final String version = attrs.getValue("version");
        final String type = attrs.getValue("type");
        final String packageName = attrs.getValue("package-name");
        final String routerLayout = attrs.getValue("routerLayout");

        RuleFlowProcess process = new RuleFlowProcess();
        process.setId(id);
        process.setName(name);
        process.setVersion(version);
        process.setType(type);
        process.setPackageName(packageName);
        if (routerLayout != null) {
            process.setMetaData("routerLayout", new Integer(routerLayout));
        }

        ((ProcessBuildData) parser.getData()).addProcess(process);

        return process;
    }

    public Object end(final String uri,
            final String localName,
            final Parser parser) throws SAXException {
        parser.endElementBuilder();

        return parser.getCurrent();
    }

    public Class generateNodeFor() {
        return org.kie.api.definition.process.Process.class;
    }

}
