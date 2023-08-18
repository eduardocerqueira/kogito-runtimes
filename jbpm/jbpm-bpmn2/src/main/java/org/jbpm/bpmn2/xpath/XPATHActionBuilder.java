package org.jbpm.bpmn2.xpath;

import java.util.HashMap;
import java.util.Map;

import org.drools.compiler.compiler.DescrBuildError;
import org.drools.compiler.rule.builder.PackageBuildContext;
import org.drools.drl.ast.descr.ActionDescr;
import org.jbpm.process.builder.ActionBuilder;
import org.jbpm.process.core.ContextResolver;
import org.jbpm.workflow.core.DroolsAction;
import org.mvel2.Macro;
import org.mvel2.MacroProcessor;

public class XPATHActionBuilder
        implements
        ActionBuilder {

    private static final Map macros = new HashMap(5);
    static {
        macros.put("insert",
                new Macro() {
                    public String doMacro() {
                        return "kcontext.getKieRuntime().insert";
                    }
                });
    }

    public XPATHActionBuilder() {

    }

    public void build(final PackageBuildContext context,
            final DroolsAction action,
            final ActionDescr actionDescr,
            final ContextResolver contextResolver) {

        try {
        } catch (final Exception e) {
            context.getErrors().add(new DescrBuildError(context.getParentDescr(),
                    actionDescr,
                    null,
                    "Unable to build expression for action '" + actionDescr.getText() + "' :" + e));
        }
    }

    public static String processMacros(String consequence) {
        MacroProcessor macroProcessor = new MacroProcessor();
        macroProcessor.setMacros(macros);
        return macroProcessor.parse(delimitExpressions(consequence));
    }

    /**
     * Allows newlines to demarcate expressions, as per MVEL command line.
     * If expression spans multiple lines (ie inside an unbalanced bracket) then
     * it is left alone.
     * Uses character based iteration which is at least an order of magnitude faster then a single
     * simple regex.
     */
    public static String delimitExpressions(String s) {

        StringBuilder result = new StringBuilder();
        char[] cs = s.toCharArray();
        int brace = 0;
        int sqre = 0;
        int crly = 0;
        char lastNonWhite = ';';
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            switch (c) {
                case '(':
                    brace++;
                    break;
                case '{':
                    crly++;
                    break;
                case '[':
                    sqre++;
                    break;
                case ')':
                    brace--;
                    break;
                case '}':
                    crly--;
                    break;
                case ']':
                    sqre--;
                    break;
                default:
                    break;
            }
            if ((brace == 0 && sqre == 0 && crly == 0) && (c == '\n' || c == '\r')) {
                if (lastNonWhite != ';') {
                    result.append(';');
                    lastNonWhite = ';';
                }
            } else if (!Character.isWhitespace(c)) {
                lastNonWhite = c;
            }
            result.append(c);

        }
        return result.toString();
    }

}
