package org.jbpm.process.builder.dialect.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.drools.drl.ast.descr.ProcessDescr;
import org.drools.util.StringUtils;
import org.jbpm.process.builder.ProcessBuildContext;
import org.jbpm.process.builder.ProcessClassBuilder;

public class JavaProcessClassBuilder
        implements
        ProcessClassBuilder {

    protected static List<String> systemImports = new ArrayList<>();
    static {
        systemImports.add(org.drools.core.util.KieFunctions.class.getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.drools.core.rule.builder.dialect.java.RuleClassBuilder#buildRule(org.drools.core.rule.builder.BuildContext, org.drools.core.rule.builder.dialect.java.BuildUtils, RuleDescr)
     */
    public String buildRule(final ProcessBuildContext context) {
        // If there is no compiled code, return
        if (context.getMethods().isEmpty()) {
            return null;
        }

        final String lineSeparator = System.getProperty("line.separator");

        final StringBuilder buffer = new StringBuilder();
        buffer.append("package " + context.getPkg().getName() + ";" + lineSeparator);
        if (context.getProcess() != null && ((org.jbpm.process.core.Process) context.getProcess()).getImports() != null) {
            for (String decl : ((org.jbpm.process.core.Process) context.getProcess()).getImports()) {
                buffer.append("import " + decl + ";" + lineSeparator);
            }
        }

        for (String systemImport : systemImports) {
            if (!context.getPkg().getImports().containsKey(systemImport)) {
                buffer.append("import ").append(systemImport).append(";").append(lineSeparator);
            }
        }

        for (final Iterator it = context.getPkg().getStaticImports().iterator(); it.hasNext();) {
            buffer.append("import static " + it.next() + ";" + lineSeparator);
        }

        final ProcessDescr processDescr = context.getProcessDescr();

        buffer.append("public class " + StringUtils.ucFirst(processDescr.getClassName()) + " {" + lineSeparator);
        buffer.append("    private static final long serialVersionUID = 510l;" + lineSeparator);

        // @TODO record line numbers for each Action method
        for (int i = 0, size = context.getMethods().size(); i < size; i++) {
            buffer.append(context.getMethods().get(i) + lineSeparator);
        }

        final String[] lines = buffer.toString().split(lineSeparator);

        buffer.append("}");

        return buffer.toString();
    }
}
